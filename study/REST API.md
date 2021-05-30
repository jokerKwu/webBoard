# REST API에 대해서 알아보기 전에 REST에 대해서 알아 보겠습니다.

# REST란?
REST는 프로토콜이나 표준이 아닌 아키텍처 원칙 세트이다.<BR>
"Representational State Transfer"의 약자로 자원을 이름(자원의 표현)으로 구분하여 해당 자원의 상태(정보)를 주고 받는 모든 것을 의미한다.<BR>
즉, 자원의 표현에 의한 상태 전달<BR>
HTTP URI(Uniform Resource Identifier)를 통해 자원(Resource)을 명시하고, HTTP Method(POST, GET, PUT, DELETE)를 통해 해당 자원에 대한 CRUD Operation을 적용하는 것을 의미한다.<BR>

- 즉, REST는 자원 기반의 구조 (ROA) 설계의 중심에 Resource가 있고 HTTP 메소드를 통해 Resource를 처리하도록 설계된 아키텍처를 의미한다.<BR>
- 웹 사이트의 이미지, 텍스트, DB 내용 등의 몯느 자우너에 고유한 ID인 HTTP URI를 부여한다.<BR>
- CRUD Operation<BR>

## REST의 장점은 무엇인가?
  
1. HTTP 프로토콜의 인프라를 그대로 사용하므로  REST API 사용을 위한 별도의 인프라를 구축할 필요가 없다.
2. HTTP 프로토콜의 표준을 최대한 활용하여 여러 추가적인 장점을 함께 가져갈 수 있게 해준다.
3. HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 사용이 가능하다.
4. Hypermedia API의 기본을 충실히 지키면서 범용성을 보장한다.
5. REST API 메시지가 의도하는 바를 명확하게 나타내므로 의도하는 바를 쉽게 파악할 수 있다.
6. 여러가지 서비스 디자인에서 생길 수 있는 문제를 최소화한다.
7. 서버와 클라이언트의 역할을 명확하게 분리한다.

## REST의 단점은 무엇인가?
1. 표준이 존재하지 않는다.
2. 사용할 수 있는 메서드가 4가지 밖에 없다. (HTTP 메소드)
3. 브라우저를 통해 테스트할 일이 많은 서비스라면 쉽게 고칠 수 있는 URL보다 Header 값이 왠지 더 어렵게 느껴진다.
4. 구형 브라우저가 아직 제대로 지원해주지 못하는 부분이 존재한다.

## REST가 필요한 이유
- 다양한 클라이언트가 등장
- 최근의 서버 프로그램은 다양한 브라우저와 안드로이드폰, 아이폰과 같은 모바일 디바이스에서도 통신을 할 수 있어야 한다.
- 애플리케이션 분리 및 통합
- 이러한 멀티 플랫폼에 대한 지원을 위해 서비스 자원에 대한 아키텍처를 세우고 이용하는 방법을 모색한 결과, REST에 관심을 가지게 되었다.

# REST 구성 요소.
1. 자원(Resource) : URI
  - 모든 자원에 고유한 ID가 존재하고, 이 자원은 Server에 존재한다.
  - 자원을 구별하는 ID는 HTTP URI이다.
  - Client는 URI를 이용해서 자원을 지정하고 해당 자원의 상태(정보)에 대한 조작을 Server에 요청한다.
2. 행위(Verb) : HTTP Method
	- HTTP 프로토콜의 Method를 사용한다.
	- HTTP 프로토콜은 GET,POST,PUT,DELETE와 같은 메서드를 제공한다.
3. 표현
	- Client가 자원의 상태(정보)에 대한 조작을 요청하면 Server는 이에 적절한 응답을 보낸다.
	- REST에서 하나의 자원은 JSON, XML, TEXT, RSS 등 여러 형태의 Representation으로 나타내어 질 수 있다.
	- JSON 혹은 XML를 통해 데이터를 주고 받는 것이 일반적이다.

# REST 특징
1. Server-Client(서버-클라이언트 구조)
2. Stateless(무상태)
3. Cacheable(캐시 처리 가능)
4. Layered System(계층화)
5. Code-On-Demand(optional)
6. Uniform Interface(인터페이스 일관성)

# REST API란?
REST API를 알아보기전에 API(Application Programming Interface)에 개념에 대해서 알아보겠습니다.<BR>
API란 데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환가능 하도록 하는 것이다.<BR>

## REST API의 정의
REST 기반으로 서비스 API를 구현한 것이다.<BR>
최근 OpenAPI(누구나 사용할 수 있도록 공개된 API: 구글 맵, 공공 데이터 등), <BR>
마이크로 서비스(하나의 큰 애플리케이션을 여러 개의 작은 애플리케이션으로 쪼개어 변경과 조합이 가능하도록 만든 아키텍처) 등을 제공하는 업체 대부분은 REST API를 제공한다.<BR>

### REST API의 특징
사내 시스템들도 REST 기반으로 시스템을 분산해 확장성과 재사용성을 높여 유지보수 및 운용을 편리하게 할 수 있다.<BR>
REST는 HTTP 표준을 기반으로 구현하므로, HTTP를 지원하는 프로그램 언어로 클라이언트, 서버를 구현할 수 있다.<BR>
즉, REST API를 제작하면 델파이 클라이언트 뿐 아니라, 자바, C#, 웹 등을 이용해 클라이언트를 제작할 수 있다.<BR>

