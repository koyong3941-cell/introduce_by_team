import styled from "styled-components";
import { theme } from "../styles/theme";

export const Grid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 18px;
  background: #dff1ff;
`;

export const Card = styled.article`
  background: #ffffff;
  border: 2px solid #b8d9ff;
  border-radius: 14px;
  padding: 16px;
  transition: all 0.15s ease;
  &:hover {
    border-color: #7cb5ff;
    transform: translateY(-2px);
  }
`;

export const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 12px;
`;

export const Writer = styled.div`
  font-size: 13px;
  font-weight: 700;
  color: #4682b4;
`;

export const Title = styled.h3`
  margin: 0 0 12px;
  font-size: 16px;
  font-weight: 700;
  color: #333;
`;

export const Time = styled.div`
  font-size: 12px;
  color: #888;
`;

export const Content = styled.p`
  margin: 0;
  color: #555;
  font-size: 13px;
  line-height: 1.6;
  min-height: 80px;
`;

export const Footer = styled.div`
  display: flex;
  gap: 12px;
  margin-top: 14px;
  padding-top: 12px;
  border-top: 1px dashed #cddff7;

  font-size: 12px;
  color: #777;
`;
