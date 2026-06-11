import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "./assets/vite.svg";
import heroImg from "./assets/hero.png";
import "./App.css";
import Header from "./header/Header";
import Footer from "./footer/Footer";
import Board from "./board/Board";
import { Route, Routes } from "react-router-dom";

function App() {
  return (
    <>
      <Header />

      <Routes>
        <Route path="/board" element={<Board />} />
      </Routes>
      <Footer />
    </>
  );
}

export default App;
