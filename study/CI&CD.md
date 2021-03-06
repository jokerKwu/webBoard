# DevOps 란?
CI/CD 는 DevOps 엔지니어의 핵심 업무라고 한다.<br>
DevOps 란 Development와 Operation의 합성어로 소프트웨어 개발자와 정보기술 전문가 간의 소통, <br>
협업 및 통합을 강조하는 개발 환경이나 문화를 말한다.<br>
소프트웨어 개발조직과 운영조직간의 상호 의존적 대응이며 조직이 소프트웨어 제품과 서비스를 빠른 시간에 개발 및 배포하는 것을 목적으로 한다.<br>

# CI/CD의 전체적인 개념
애플리케이션 개발 단계를 자동화하여 보다 짧은 주기로 고객에게 제공하는 방법으로 핵심적인 부분만 말하자면 개발 - 빌드 - 테스트 - 배포까지의 전 과정을 자동화하는 것이 CI/CD이다.<br>
이러한 구축 사례를 일반적으로 CI/CD 파이프라인이라고 부르며 애자일 방식의 협력을 통해 지원한다.<br>
지속적인 통합(Continuous Integration), 지속적인 제공(Continuous Delivery), 지속적인 배포(Continuous Deployment)로 구성된다.<br>


## CI (Continuous Integration) 란?
지속적인 통합이라는 의미이다. (애플리케이션의 새로운 코드 변경 사항이 정기적으로 빌드 및 테스트 되어 공유 레포지토리에 통합하는 것을 의미한다.)
CI를 통해 소스코드를 검증하고 검증된 소프트웨어를 실제 프로덕션 환경으로 배포한다.

### CI 특징
- 자동화된 빌드와 테스트를 통합 에러 조기 검증으로 단위코드의 품질을 향상
- 개발자를 위한 자동화 프로세스(개발자간의 코드 충돌을 방지하기 위한 목적)
- 정기적인 빌드 및 테스트(유닛테스트 및 통합테스트)를 거쳐 공유 레포지터리에 병합되는 과정
- 클래스와 기능에서부터 전체 애플리케이션을 구성하는 서로 다른 모듈에 이르기까지 모든 것에 대한 테스트를 수행
- 동화된 테스트에서 기존 코드와 신규 코드 간의 충돌이 발견되면 CI를 통해 이러한 버그를 빠르게 수정 가능

### CI를 적용할 때의 흐름
1. 개발자는 자신이 개발한 소프트웨어의 소스코드를 공통된 버전 관리시스템(github 등)에 저장한다.
2. 소스코드상에 변동이 생기면 버전 관리 시스템에서는 CI툴로 소스코드 변경을 알린다.
3. CI툴에서는 변경된 소스코드를 대상으로 빌드,테스트,머지를 진행한다. 이 과정들이 완료되면 슬랙,카톡,메일 등을 통해 결과를 알린다.

## CD(Continuous Deployment) 란?
CI를 통해 검증된 소스코드를 지속적으로 배포해야 하기 때문에, CD를 구현하기 위해서는 CI가 우선적으로 진행되어야 합니다.

- CI/CD 파이프라인의 마지막 단계
- 프로덕션 준비가 완료된 빌드를 코드 리포지토리에 자동으로 릴리스하는 지속적 제공의 확장된 형태
- 애플리케이션을 프로덕션으로 릴리스하는 작업을 자동화
- Continuous Delivery로 통칭하여 언급하기도 함
- 개발자가 애플리케이션에 변경 사항을 작성한 후 몇 분 이내에 애플리케이션을 자동으로 실행할 수 있는 것을 의미
- 테스트와 빌드가 ‘지속적’으로 이루어지기 때문에, 배포 또한 자연스럽게 ‘지속적’으로 이루어지게 됨
