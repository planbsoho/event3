| 기능           | Method | URL                     | Request                                                                                   | Response                                                                                      | 상태코드         |
|----------------|--------|--------------------------|--------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|------------------|
| 할일 등록      | POST   | /event                   | {"title": "할일", "name": "작성자명", "password": "비밀번호", "createdAt": "날짜", "updatedAt": "날짜"} | {"id": 1, "title": "할일", "name": "작성자명", "createdAt": "날짜", "updatedAt": "날짜"}         | 201 Created      |
| 전체 조회 (이름으로) | GET    | /event?name=이름        | 이름                                                                 | [{"id": 1, "createdAt": "날짜", "updatedAt": "날짜"}, ...]                                     | 200 OK           |
| 단건 조회 (ID로)     | GET    | /event/{id}             | id                                                                                       | {"id": 1, "name": "작성자명", "title": "할일", "createdAt": "날짜", "updatedAt": "날짜"}       | 200 OK           |
| 할일 수정       | PUT    | /event/{id}             | {"id": 1, "password": "비밀번호", "title": "새 할일", "name": "작성자명"}                  | {"id": 1, "title": "새 할일", "name": "작성자명", "updatedAt": "수정된 날짜"}                   | 200 OK           |
| 할일 삭제       | DELETE | /event/{id}             | {"id": 1, "password": "비밀번호"}                                                          | 없음                                                                                          | 204 No Content   |



![스크린샷 2025-05-14 101238](https://github.com/user-attachments/assets/cc574fac-cbf1-4f1d-be78-809173ceab5e)
