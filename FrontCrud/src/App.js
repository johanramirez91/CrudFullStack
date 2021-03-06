import React, {
  createContext,
  useContext,
  useReducer,
  useEffect,
  useRef,
  useState
} from "react";

const HOST_API = "http://localhost:8080/api";

const initialState = {
  list: [],
  item: {}

};

const Store = createContext(initialState);

/* Función que inicia el formulario y realiza la petición a la API */
const Form = () => {
  const formRef = useRef(null);
  const { dispatch, state: { item } } = useContext(Store);
  const [state, setState] = useState({ item });

  const onAdd = (event) => {
    event.preventDefault();

    const request = {
      name: state.name,
      id: null,
      isCompleted: false
    };

    fetch(HOST_API + "/todo", {
      method: "POST",
      body: JSON.stringify(request),
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then(response => response.json())
      .then((todo) => {
        dispatch({ type: "add-item", item: todo });
        setState({ name: "" });
        formRef.current.reset();
      });
  };

  return (
    <form ref={formRef}>
      <input
        type="text"
        name="name"
        onChange={(event) => {
          setState({ ...state, name: event.target.value });
        }}
      ></input>
      <button onClick={onAdd}>Agregar</button>
    </form>
  );
};

/* Se usa el conexto de la respuesta y se retorna la tabla con los datos */
const List = () => {
  const { dispatch, state } = useContext(Store);

  useEffect(() => {
    fetch(HOST_API + "/todos")
      .then(response => response.json())
      .then((list) => {
        dispatch({ type: "update-list", list });
      });
  }, [state.list.length, dispatch]);

  return (
    <div>
      <table>
        <thead>
          <tr>
            <td>ID</td>
            <td>Nombre</td>
            <td>Descripcion</td>
            <td>Esta completado?</td>
          </tr>
        </thead>
        <tbody>
          {state.list.map((todo) => {
            return (
              <tr key={todo.id}>
                <td>{todo.id}</td>
                <td>{todo.name}</td>
                <td>{todo.description}</td>
                <td>{todo.isComplete}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

/* Se actualiza el estado de la lista */
function reducer(state, action) {
  switch (action.type) {
    case 'update-list':
      return { ...state, list: action.list };
    case 'add-item':
      const newList = state.list;
      newList.push(action.item);
      return { ...state, list: newList };
    default:
      return state;
  }
}

const StoreProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  return (
    <Store.Provider value={{ state, dispatch }}>{children}</Store.Provider>
  );
};

/* Función principal */
function App() {
  return (
    <StoreProvider>
      <Form />
      <List />
    </StoreProvider>
  );
}

export default App; //se exporta el componente App