import "./App.css";
import Footer from "./components/layout/footer/Footer";
import Header from "./components/layout/header/Header";

import { Routes, Route } from "react-router-dom";
import BoardList from "./fratures/board/BoardList";

function App() {
  return (
    <>
      <Header />

      <Routes>
        <Route path="/" element={<div style={{ height: "600px" }}></div>} />
        <Route path="/boards" element={<BoardList />} />
      </Routes>

      <Footer />
    </>
  );
}

export default App;
