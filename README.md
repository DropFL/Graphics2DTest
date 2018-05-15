Graphics2DTest (가명) 프로젝트 구조
=================================
# 1. 패키지 분류

## 1.1 res
게임에 포함된 리소스가 들어있는 패키지이다. 리소스의 종류(그림, 음악 등)에 따라 하위 디렉토리로 분류되어 있다. 이 패키지에는 리소스 외에도 `XResource` 의 이름을 갖는 Enumerator들이 있다. 이는 *X* 타입의 리소스 파일과 프로그램을 연결해주며 해당 리소스와 관련된 프로세스를 내부적으로 처리해주는 역할을 한다.   
예를 들어, `ImageResource`는 `res/images` 디렉토리 내의 각 이미지 파일과 일대일로 매칭된 객체들을 갖고 있으며, 각 파일로부터 `ImageIcon`을 가져오는 `getImageIcon()` 메서드를 갖고 있다.

## 1.2 libs
게임에 포함된 라이브러리 파일들이 들어있는 디렉토리이다.

## 1.3 com
### 1.3.1 com.dropfl
[DropFL](https://github.com/DropFL)이 주로 기여한, `Main`과 `GameFrame`을 비롯하여 GUI와 밀접하게 관련된 것으로 구성된 패키지.

* `com.dropfl.activity` : `Activity`와 이를 상속한 클래스들이 있는 패키지.

* `com.dropfl.component` : 렌더링과 관련된 인터페이스가 있는 패키지. 현재는 `IDrawable` 인터페이스만 속해있다.

* `com.dropfl.music` : 음악 재생과 관련된 클래스가 있는 패키지. 현재는 `MusicPlayer` 클래스만 속해있다.

### 1.3.2 com.rshtiger
[rshtiger](https://github.com/rshtiger)가 주로 기여한, `Engine`을 비롯하여 게임 그 자체에 관련된 것으로 구성된 패키지.

* `com.rshtiger.key` : 키 입력과 관련된 소스들이 있는 패키지.

* `com.rshtiger.platformer` : 게임의 플랫포머 엔진에 관련된 소스들이 있는 패키지.

***
# 2. 핵심 객체
프로그램의 구조를 이해하는 데에 필요한 객체들은 다음과 같다.

## 2.1 Main
`main` 함수가 선언된 위치이자, 전역 설정을 담당하는 곳이다. 현재는 프로그램의 해상도가 (1280x720) 지정되어 있으며, 추후 `OptionActivity`에서의 설정 변경을 이 곳에 있는 변수를 변경하고 다른 `Activity`에서 이를 참조하는 방식으로 구현할 예정이다.

## 2.2 Activity
안드로이드의 액티비티 개념과 유사하다. 하나의 화면에 대해 일어나는 모든 작업들을 총괄하는 객체이다. `GameFrame`에서는 `activity` 객체를 치환하는 방식으로 화면의 변화를 구현할 예정이다. 다만 안드로이드에서 이 작업에 필요한 `Intent`의 필요성을 인지하고 있지만, 여기서 어떻게 구현할지는 아직 결정되지 않았다.

### 2.2.1 IDrawable
화면에 렌더링할 수 있는 객체들은 모두 이 인터페이스를 구현해야한다. 메서드는 단 하나만 정의되어있다.

	void render (java.awt.Graphics2D);
이 인터페이스는 프로젝트 전체에 걸져 범용적으로 쓰이고 있다. 예를 들어, 앞서 설명한 `Activity`와 `com.rshtiger.platformer`에 있는 게임과 관련된 오브젝트들 또한 이를 구현하고 있다. 프로젝트에서 이 메서드가 쓰인 곳을 살펴보면 재귀적으로 구성되어 있음을 알 수 있다.

## 2.3 Player, IPlayerInteractive
말 그대로 플레이어, 플레이어와 상호작용하는 다른 객체들에 대한 인터페이스이다. 둘 모두 `IDrawable`을 구현/상속하고 있다. `IPlayerInteractive`에는 다음의 두 메서드가 정의되어 있다.

	boolean isTouched (com.rshtiger.platformer.Player);
	void interact (com.rshtiger.platformer.Player);
각각 플레이어와 접촉했는지 확인하는 메서드, 플레이어와 상호작용하는 메서드이다. 지금은 `Block`이나 `Bullet`, `Laser` 등이 이를 구현하는 것으로 계획하고 있으며, `interact` 메서드에 `boolean`형 리턴값을 추가하여 `Bullet`과 같이 플레이어와 상호작용 후 제거가 필요한 객체의 경우 필요에 따라 `true`를 리턴해 제거하게끔 하는 방법도 고려하고 있다. (다만 이 경우 `destroy`와 같은 제거용 메서드가 추가로 필요하다.)

## 2.4 Key, KeyStatus
`KeyStatus`는 키 입력을 주관하는 클래스로, 아예 인스턴스화할 수 없게끔 되어있다. `KeyStatus.init()`으로 초기화를 진행하고 `KeyStatus.register(java.awt.Component)`로 해당 `Component`에 들어오는 입력을 받을 수 있다. 키 입력을 확인하는 메서드는 다음과 같다.

	boolean isKeyPressed (com.rshtiger.key.Key);
	boolean isKeyJustPressed (com.rshtiger.key.Key);
전자는 키가 단순히 눌려있는지를 조사하며, 후자는 키 입력이 아직 처리되지 않았는지를 추가로 조사한다. `KeyStatus`에게 키 입력이 처리되었음을 알려주는 메서드는 다음과 같다.

	void setKeyProcessed (com.rshtiger.key.Key);
멀티쓰레딩 환경에서는 다소 위험성이 있는 방식이지만, 이 프로젝트에서는 최대 1개의 객체가 키 입력에 반응하기 때문에 큰 무리가 없다고 판단하였다.

***
# 3. 프로그램 구조
`Main`에서 `KeyStatus`를 초기화하고 `GameFrame` 인스턴스를 생성하여 이를 `KeyStatus`에 등록한다. `GameFrame`은 `JFrame`을 상속하는 클래스로 실제로는 여기의 `paint` 메서드를 통해 렌더링이 일어난다. 이 때 사용하는 Double Buffering 기법에서 첫번째 렌더링을 `Activity` 에게 온전히 위임하고 두번째 렌더링으로써 실제 화면에 표시하는 작업을 수행한다.

그 중에서도 `PlatformerActivity`는 렌더링을 3단계로 나누어 수행하는데, 이는 다음과 같다.
1. 게임 플레이 화면보다 **뒤에** 와야하는 요소들의 렌더링 (배경화면 등)
2. 실제 게임 플레이 화면 렌더링 (`Engine`에게 위임)
3. 게임 플레이 화면보다 **앞에** 와야하는 요소들의 렌더링 (게임 내 이펙트 포함, 현재는 해당 요소가 없음)

`Engine`에서도 `Player` 객체와 `IPlayerInteractive` 객체들을 대상으로 `render`를 호출하는 방식으로 렌더링을 수행한다. `Engine`은 `Thread`를 상속하여 자체적인 `tick()` 함수를 통해 한 프레임씩 게임을 진행시킨다. `PlatformerActivity`에서는 `Engine.start()`를 호출해주면 된다.

## 3.1 TODO
1. `Engine`의 시간 진행은 게임 특성상 `MusicPlayer`의 진행과 동기화되어야하기 때문에, `Engine.updateTime(time)` 함수를 만드는 것을 고려하고 있다. (필요에 따라서는 `MusicProgressListener`를 만들 수도 있다.)
2. 화면 전체에 걸친 효과(확대/축소 등)를 `Engine` 또는 `Activity`의 위치에서도 적용할 수 있지만, `Graphics2D` 로 할 수 있을지 검증되지 않았으며, 경우에 따라서는 `GameFrame`의 `paint`에서 이를 처리해야할 수도 있을 것이다.
3. 이 모든 작업이 fps 저하 없이 매끄럽게 진행될 수 있을지도 확인해야 한다.
