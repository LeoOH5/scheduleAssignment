# 일정 관리 애플리케이션

Spring Boot 기반의 일정 관리 및 댓글 기능을 제공하는 RESTful API 서버입니다.

```

**관계:**

- 1개의 Schedule은 여러 개의 Comment를 가질 수 있습니다 (1:N)
- 1개의 Schedule당 최대 10개의 Comment만 작성 가능합니다

---

## API 명세서

### Schedule (일정) API

#### 1. 일정 생성

```
POST /schedules
Content-Type: application/json

Request Body:
{
  "title": "회의",
  "description": "팀 회의",
  "userName": "홍길동",
  "password": "1234"
}

Response: 201 Created
{
  "id": 1,
  "title": "회의",
  "description": "팀 회의",
  "userName": "홍길동",
  "createdAt": "2025-10-21T14:00:00",
  "modifiedAt": "2025-10-21T14:00:00"
}
```

#### 2. 특정 일정 조회 (userName으로 조회)

```
GET /schedules/{userName}

Response: 200 OK
{
  "id": 1,
  "title": "회의",
  "description": "팀 회의",
  "userName": "홍길동",
  "createdAt": "2025-10-21T14:00:00",
  "modifiedAt": "2025-10-21T14:00:00"
}
```

#### 3. 전체 일정 조회

```
GET /schedules

Response: 200 OK
[
  {
    "id": 1,
    "title": "회의",
    "description": "팀 회의",
    "userName": "홍길동",
    "createdAt": "2025-10-21T14:00:00",
    "modifiedAt": "2025-10-21T14:00:00"
  },
  ...
]
```

#### 4. 일정 수정

```
PUT /schedules/{userName}
Content-Type: application/json

Request Body:
{
  "title": "수정된 회의",
  "userName": "홍길동"
}

Response: 200 OK
{
  "id": 1,
  "title": "수정된 회의",
  "userName": "홍길동",
  "modifiedAt": "2025-10-21T15:00:00"
}
```

#### 5. 일정 삭제

```
DELETE /schedules/{id}

Response: 200 OK
```

---

### Comment (댓글) API

#### 댓글 생성

```
POST /schedules/{scheduleId}/comments
Content-Type: application/json

Request Body:
{
  "content": "좋은 일정이네요!",
  "writer": "김철수",
  "password": "5678"
}

Response: 201 Created
{
  "id": 1,
  "scheduleID": 1,
  "content": "좋은 일정이네요!",
  "writer": "김철수",
  "createdAt": "2025-10-21T14:30:00",
  "modifiedAt": "2025-10-21T14:30:00"
}

※ 한 일정당 최대 10개의 댓글만 작성 가능
```
