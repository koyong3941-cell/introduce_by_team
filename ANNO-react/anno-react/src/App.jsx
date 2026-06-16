import "./App.css";
import Footer from "./components/layout/footer/Footer";
import Header from "./components/layout/header/Header";

import { Routes, Route } from "react-router-dom";
import BoardList from "./fratures/board/BoardList";
import BoardDetail from "./fratures/board/BoardDetail";
import BoardForm from "./fratures/board/BoardForm";
import Login from "./login/Login";

function App() {
  return (
    <>
      <Header />

      <Routes>
        <Route path="/" element={<div style={{ height: "600px" }}></div>} />
        <Route path="/boards" element={<BoardList />} />
        <Route path="/boards/:boardNo" element={<BoardDetail />} />
        <Route path="/boards/:boardNo/edit" element={<BoardForm />} />
        <Route path="/boards/write" element={<BoardForm />} />
        <Route path="/login" element={<Login />} />
      </Routes>

      <Footer />
    </>
  );
}

export default App;
