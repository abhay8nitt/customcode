package ttllru;

public class TTLCacheTest {
    public static void main(String[] args) throws InterruptedException {
        TTLCacheImpl<String,String> impl = new TTLCacheImpl();
        impl.put("Mani","Mishra");
        impl.put("Abhay","Kumar Mishra");
        impl.get("Abhay");
        impl.put("Amit","Saha");

        Thread.sleep(2000);
        System.out.println(impl.size());
    }
}
