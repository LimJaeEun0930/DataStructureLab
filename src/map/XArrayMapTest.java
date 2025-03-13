package map;

public class XArrayMapTest {
    public static void main(String[] args) {
        XMap<Integer, Integer> map = new XArrayMap<>();

        map.put(4, 1);
        System.out.println(map.size());
        map.put(14, 2);
        System.out.println(map.size());

//// 요소 추가
//map.put("Alice", 25);
//map.put("Bob", 30);
//map.put("Charlie", 35);
//System.out.println(map.get("Alice")); // 출력: 25
//
//// 키 존재 여부 확인
//System.out.println(map.containsKey("Bob")); // 출력: true
//System.out.println(map.containsValue(40)); // 출력: false
//
//// 요소 삭제
//map.remove("Charlie");
//System.out.println(map.containsKey("Charlie")); // 출력: false
//
//// 모든 키와 값 출력
//System.out.println("Keys: " + map.keySet()); // 출력: [Alice, Bob]
//System.out.println("Values: " + map.values()); // 출력: [25, 30]
//
//// 전체 삭제 후 확인
//map.clear();
//System.out.println(map.isEmpty()); // 출력: true

    }
}
