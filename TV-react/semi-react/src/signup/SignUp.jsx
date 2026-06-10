import { useState } from "react";
import axios from "axios";

const SignUp = () => {
  const [memberId, setMemberId] = useState("");
  const [memberPwd, setMemberPwd] = useState("");
  const [memberName, setMemberName] = useState("");

  const onChangeId = (e) => {
    setMemberId(e.target.value);
  };
  const onChangePwd = (e) => {
    setMemberPwd(e.target.value);
  };
  const onChangeName = (e) => {
    setMemberName(e.target.value);
  };

  const onSubmit = () => {
    axios
      .post("http://localhost/api/members", {
        memberId,
        memberPwd,
        memberName,
      })
      .then((e) => {
        console.log(e);
      });
  };

  return (
    <>
      <form>
        아이디 : <input onChange={onChangeId} /> <br />
        비밀번호 : <input onChange={onChangePwd} /> <br />
        이름 : <input onChange={onChangeName} /> <br />
        <button onClick={onSubmit}>회원가입</button>
      </form>
    </>
  );
};

export default SignUp;
