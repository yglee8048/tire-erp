# Domain

- 도메인 모듈에는 도메인 관련 내용이 작성된다.
  - entity, repository, service
- **도메인 모듈은 서비스 비즈니스를 몰라야 한다.**
  - 도메인 자체에만 집중한다.
  - 서비스 비즈니스에 종속적인 쿼리나 로직이 필요한 경우, 해당 모듈에 내용을 작성한다.