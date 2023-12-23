jar 파일 생성후 실행시, 본래 코드와 다르게 동작해서 jar파일을 올리지 않았습니다.

 <br/>
 <br/>
 
[  API 명세서  ]

▶ 멤버 초대(임시 멤버 생성)

- HTTP METHOD : POST
- URL : /member/invite
- Request parameter

|name|type|Required|Descrpition|
|------|---|---|---|
|name|String|O|이름|
|email|String|O|이메일|
|phoneNumber|String|O|전화번호|

```
{
    "email":"test@test.com",
    "phoneNumber":"01000001234",
    "name":"테스터"
}
```

- Response

|name|type|Descrpition|
|------|---|---|
|url|String|접속 url|

```
{
    "result": "SUCCESS",
    "status": 200,
    "data": {
        "url": "/member/invite/U38YiENkpsUaS231223010431"
    }
}
```
<br/>
<br/>  
▶ 초대 링크 접속 (임시 회원 활성화)

- HTTP METHOD : POST
- URL : /member/invite/{inviteToken}
- Request URL Example

```
http://localhost/member/invite/agInDXaRpeCZS231223193700
```

- Response Example

```
{
    "result": "SUCCESS",
    "status": 200,
    "data": "ok"
}

// 이미 접속한 링크로 재접속
{
    "result": "ERROR",
    "status": 400,
    "message": "Token is expired"
}
```

