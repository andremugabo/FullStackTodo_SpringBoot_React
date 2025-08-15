import './App.css'
import ListTodoComponent from './components/ListTodoComponent'
import { Header } from './components/Header'
import { Footer } from './components/Footer'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { TodoComponent } from './components/TodoComponent'

function App() {

  return (
    <>
    <BrowserRouter>
      <Header/>
      <Routes>
         <Route path='/' element = {<ListTodoComponent/>} ></Route>
         <Route path='/todos' element = {<ListTodoComponent/>} ></Route>
         <Route path='/create' element = {<TodoComponent/>} ></Route>
         <Route path='/update/:id' element = {<TodoComponent/>} ></Route>

      </Routes>
      <Footer/>
    </BrowserRouter>
      
    </>
  )
}

export default App
