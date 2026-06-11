import React from "react";

const traitFst = [
  "정신나간",
  "용감한",
  "무서운",
  "행복한",
  "건방진",
  "재빠른",
  "게으른",
  "신난",
  "시건방진",
  "괘씸한",
];

const traitSec = [
  "원숭이",
  "강아지",
  "황소",
  "개구리",
  "래트",
  "물범",
  "햄스터",
  "듀공",
  "돌고래",
  "기린",
];

const getAnimalNickname = (regDate) => {
  if (!regDate) return "익명";

  const date = regDate instanceof Date ? regDate : new Date(regDate);
  const millis = date.getMilliseconds(); // 0 ~ 999

  const fstIndex = Math.floor(millis / 100) % 10;
  const secIndex = millis % 10;

  return `${traitFst[fstIndex]} ${traitSec[secIndex]}`;
};

const AnimalName = ({ regDate, className = "" }) => {
  const nickname = getAnimalNickname(regDate);

  return <span className={className}>{nickname}</span>;
};

export default AnimalName;
