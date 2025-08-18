import './App.css'
import ListTodoComponent from './components/ListTodoComponent'
import { Header } from './components/Header'
import { Footer } from './components/Footer'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { TodoComponent } from './components/TodoComponent'
import Register from './components/Register'
import Login from './components/Login'
import { isUserLoggedIn } from './services/AuthService'

function App() {

  const AunthenticatedRouter = ({children}) => {
    const isAuth = isUserLoggedIn();

    if(isAuth){
      return children;
    }else {
      return <Navigate to="/"/>
    }
  }

  return (
    <>
    <BrowserRouter>
      <Header/>
      <Routes>
         <Route path='/' element = {<Login/>} ></Route>
         <Route path='/todos' element = {
          <AunthenticatedRouter>
            <ListTodoComponent/>
          </AunthenticatedRouter>
          } ></Route>
         <Route path='/create' element = {
          <AunthenticatedRouter>
            <TodoComponent/>
          </AunthenticatedRouter>
          } ></Route>
         <Route path='/update/:id' element = {
          <AunthenticatedRouter>
            <TodoComponent/>
          </AunthenticatedRouter>
          } ></Route>
         <Route path='/register' element = {<Register/>} ></Route>
         <Route path='/login' element = {<Login/>} ></Route>

      </Routes>
      <Footer/>
    </BrowserRouter>
      
    </>
  )
}

export default App
