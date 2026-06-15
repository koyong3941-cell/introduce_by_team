const RegDate = ({ regDate }) => {
  if (!regDate) return null;

  const date = new Date(regDate);
  const now = new Date();

  const isToday =
    date.getFullYear() === now.getFullYear() &&
    date.getMonth() === now.getMonth() &&
    date.getDate() === now.getDate();

  const yesterday = new Date(now);
  yesterday.setDate(now.getDate() - 1);

  const isYesterday =
    date.getFullYear() === yesterday.getFullYear() &&
    date.getMonth() === yesterday.getMonth() &&
    date.getDate() === yesterday.getDate();

  const isSameYear = date.getFullYear() === now.getFullYear();

  let formattedDate = "";

  if (isToday) {
    // 오늘 → 시간만 (14:23)
    formattedDate = date.toLocaleTimeString("ko-KR", {
      hour: "2-digit",
      minute: "2-digit",
      hour12: false,
    });
  } else if (isYesterday) {
    // 어제 → "어제 14:23"
    const time = date.toLocaleTimeString("ko-KR", {
      hour: "2-digit",
      minute: "2-digit",
      hour12: false,
    });
    formattedDate = `어제 ${time}`;
  } else if (isSameYear) {
    // 올해 (오늘 제외) → 월.일 시:분 (06.11 15:15)
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    const time = date.toLocaleTimeString("ko-KR", {
      hour: "2-digit",
      minute: "2-digit",
      hour12: false,
    });
    formattedDate = `${month}.${day} ${time}`;
  } else {
    // 다른 해 → 년.월.일 시:분 (25.06.12 14:23)
    const year = String(date.getFullYear()).slice(-2);
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    const time = date.toLocaleTimeString("ko-KR", {
      hour: "2-digit",
      minute: "2-digit",
      hour12: false,
    });
    formattedDate = `${year}.${month}.${day} ${time}`;
  }

  return <span>{formattedDate}</span>;
};

export default RegDate;
