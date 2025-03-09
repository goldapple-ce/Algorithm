# [Gold II] 얼음 미로 - 20926 

[문제 링크](https://www.acmicpc.net/problem/20926) 

### 성능 요약

메모리: 26584 KB, 시간: 296 ms

### 분류

데이크스트라, 그래프 이론, 구현, 최단 경로

### 제출 일자

2025년 3월 9일 14:50:02

### 문제 설명

<p style="text-align: center;"><img alt="" height="250px" src="https://upload.acmicpc.net/5fa6f462-a6c2-4dff-a2e7-a1ce6d9d0541/-/preview/"></p>

<p>탐험가 테라는 얼음 미로에 갇혔다. 얼음 미로의 바닥은 빙판으로 되어 있어 발을 내디디면 바위에 부딪힐 때까지 미끄러진다. 예를 들어, 위 그림에서 테라가 왼쪽 방향으로 이동한다면 중간에 멈출 수 없고 왼쪽 바위에 부딪힐 때까지 미끄러진다. 얼음 미로 바깥은 절벽이기 때문에 빠지면 탈출할 수 없다.</p>

<p>얼음 미로에는 $4$가지 오브젝트가 있다.</p>

<ol>
	<li><img alt="" height="32px" src="https://upload.acmicpc.net/1727be99-41c5-41d1-8f49-85745820114a/-/preview/">  테라 : 얼음 미로에 갇힌 탐험가. 상하좌우 $4$방향으로 이동할 수 있다. 얼음 미로에 단 $1$명의 테라만 존재한다. 테라가 최초 위치한 빙판의 <strong>미끌 시간</strong>은 $0$이다.</li>
	<li><img alt="" height="32px" src="https://upload.acmicpc.net/1019dc88-5904-48c4-8db5-748efaa17076/-/preview/">  바위 : 통과할 수 없다. 미끄러지다 부딪히면 앞에서 멈춘다.</li>
	<li><img alt="" height="32px" src="https://upload.acmicpc.net/2fb7171d-22a1-4bae-82a1-241738606465/-/preview/">  구멍 : 이곳에 빠지면 영영 탈출할 수 없다.</li>
	<li><img alt="" height="32px" src="https://upload.acmicpc.net/ed684f4e-0a35-4ffc-a961-5d9a1efdee96/-/preview/">  출구 : 이곳에 방문하는 즉시 얼음 미로를 탈출한다. 얼음 미로에 단 $1$개의 출구만 존재한다.</li>
</ol>

<p>어떤 빙판 위에서 미끄러지는 데 걸리는 시간을 <strong>미끌 시간</strong>이라고 하자. 각 빙판마다 <strong>미끌 시간</strong>은 다를 수 있다.</p>

<p>테라가 어느 한쪽 방향으로 이동할 때, 테라가 미끄러지는 동안 위치한 빙판의 <strong>미끌 시간</strong>을 더하면 이동 시간을 구할 수 있다. 단, 이동 시간 계산과 관련하여 두 가지 규칙이 있다.</p>

<p style="text-align: center;"><img alt="" height="252px" src="https://upload.acmicpc.net/4675606f-626c-453f-8e83-c562190177bf/-/preview/"></p>

<ul>
	<li>테라가 어느 한쪽 방향으로 이동을 시작할 때, 시작 빙판의 <strong>미끌 시간</strong>은 포함하지 않는다.</li>
	<li>테라가 출구로 들어갈 때, 출구 빙판의 <strong>미끌 시간</strong>은 포함하지 않는다.</li>
</ul>

<p>위 그림에서 테라가 위로 이동할 때의 이동 시간을 계산하자. 테라가 현재 서 있는, 시작 빙판의 <strong>미끌 시간</strong> $4$와 출구 빙판의 <strong>미끌 시간</strong> $0$을 제외하면 $1 + 2 = 3$ 만큼의 시간이 걸린 뒤 출구를 통해 탈출함을 알 수 있다.</p>

<p>저체온증이 시작된 테라는 얼음 미로를 가능한 한 빨리 탈출하고 싶다. 얼음 미로를 탈출하는 데 걸리는 최단 시간을 계산하자.</p>

### 입력 

 <p>첫 번째 줄에는 얼음 미로의 가로 크기를 나타내는 정수 $W$($2 \le W \le 500$), 세로 크기를 나타내는 정수 $H$($2 \le H \le 500$)가 주어진다.</p>

<p>두 번째 줄부터 $H$개의 줄에 걸쳐 얼음 미로에 대한 정보가 주어진다.</p>

<p>테라는 <span data-darkreader-inline-color="" style="color: rgb(231, 76, 60); --darkreader-inline-color:#e95849;"><code>T</code></span>, 바위는 <span data-darkreader-inline-color="" style="color: rgb(231, 76, 60); --darkreader-inline-color:#e95849;"><code>R</code></span>, 구멍은 <span data-darkreader-inline-color="" style="color: rgb(231, 76, 60); --darkreader-inline-color:#e95849;"><code>H</code></span>, 출구는 <span data-darkreader-inline-color="" style="color: rgb(231, 76, 60); --darkreader-inline-color:#e95849;"><code>E</code></span>로 나타낸다.</p>

<p>빙판의 <strong>미끌 시간</strong> $t$는 $0$ 이상 $9$ 이하의 정수로 나타낸다.</p>

### 출력 

 <p>얼음 미로를 탈출할 수 있다면 최단 탈출 시간을 출력한다.</p>

<p>얼음 미로를 탈출할 수 없다면 <span data-darkreader-inline-color="" style="color: rgb(231, 76, 60); --darkreader-inline-color:#e95849;"><code>-1</code></span>을 출력한다.</p>

