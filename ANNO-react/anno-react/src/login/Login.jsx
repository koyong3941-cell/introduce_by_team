import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
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
import { useAuth } from "../context/AuthContext";

const Login = () => {
  const { login, user } = useAuth(); // 🔥 user 추가
  const [adminId, setAdminId] = useState("");
  const [adminPwd, setAdminPwd] = useState("");
  const [status, setStatus] = useState("");
  const [loading, isLoading] = useState(false);
  const navi = useNavigate();

  const onSubmit = async () => {
    if (!adminId || !adminPwd) {
      setStatus("아이디랑 비밀번호를 꼭 입력하세요");
      return;
    }

    isLoading(true);
    setStatus("");

    try {
      const result = await api.post("/auth/login", {
        adminId,
        adminPwd,
      });

      login(result.data);
      alert("로그인 성공!");

      navi("/");
    } catch (err) {
      console.log(err.response);
      setStatus("아이디 또는 비밀번호가 올바르지않습니다");
    } finally {
      isLoading(false);
    }
  };

  return (
    <Page>
      <Card>
        <Title>로그인</Title>
        <Sub>로그인</Sub>

        {/* 🔥 로그인 상태 표시 */}
        {user ? (
          <Message style={{ color: "green" }}>
            로그인 상태입니다 ({user.adminId || user.userId})
          </Message>
        ) : (
          <>
            <Field>
              <Label>아이디</Label>
              <Input
                placeholder="아이디를 입력하세요"
                onChange={(e) => setAdminId(e.target.value)}
              />
            </Field>

            <Field>
              <Label>비밀번호</Label>
              <Input
                placeholder="비밀번호를 입력하시오~"
                type="password"
                onChange={(e) => setAdminPwd(e.target.value)}
              />
            </Field>

            <Button onClick={onSubmit} disabled={loading}>
              {loading ? "로그인 하는중...." : "로그인"}
            </Button>
          </>
        )}

        {status && <Message>{status}</Message>}
      </Card>
    </Page>
  );
};

export default Login; 