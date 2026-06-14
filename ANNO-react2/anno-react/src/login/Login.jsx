import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import axios from "axios";
import {
  Button,
  Card,
  Field,
  Input,
  Label,
  Message,
  Page,
  Sub,
  Title,
} from "../styles/AuthForm.styles";

const Login = () => {
  const [memberId, setMemberId] = useState("");
  const [memberPwd, setMemberPwd] = useState("");
  const [status, setStatus] = useState("");
  const [loading, isLoading] = useState(false);
  const navi = useNavigate();

  const onChangeId = (e) => {
    setMemberId(e.target.value);
  };
  const onChangePwd = (e) => {
    setMemberPwd(e.target.value);
  };
  const onSubmit = async () => {
    if (!memberId || !memberPwd) {
      setStatus("아이디랑 비밀번호를 꼭입력하세요");
      return;
    }
    isLoading(true);
    setStatus("");
    try {
      const result = await api.post("/login", {
        userId,
        userPwd,
      });
      console.log(result.data);

      alert("로그인 성공!");

      navi("/");
    } catch (err) {
      console.log(err.response);
      setStatus("아이디 또는 비밀번호가 올바르지않습니다");
    } finally {
      isLoading(false);
    }
  };
  const onKeyDown = (e) => {
    if (e.key == "Enter") onSubmit();
  };
  return (
    <Page>
      <Card>
        <Title>로그인</Title>
        <Sub>로그인 해주세요~~</Sub>

        <Field>
          <Label>아이디</Label>
          <Input placeholder="아이디를 입력하세요" onChange={onChangeId} />
        </Field>
        <Field>
          <Label>비밀번호</Label>
          <Input
            placeholder="비밀번호를 입력하시오~"
            type="password"
            onChange={onChangePwd}
          />
        </Field>
        <Button onClick={onSubmit} onKeyDown={onKeyDown} disabled={loading}>
          {loading ? "로그인 하는중...." : "로그인"}
        </Button>
        {status.length > 0 && <Message>{status}</Message>}
      </Card>
    </Page>
  );
};

export default Login;
