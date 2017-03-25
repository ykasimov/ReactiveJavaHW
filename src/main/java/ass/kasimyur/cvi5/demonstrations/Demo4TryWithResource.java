package ass.kasimyur.cvi5.demonstrations;

import java.util.function.Consumer;

class Resource1 {
	
	protected String name;
	
	public Resource1(String name) {
		this.name = name;
	}

	public void doWork() {
		System.out.println("Worked " + name);
	}
	
	@Override
	public void finalize() {
		System.out.println("Closed " + name);
	}
	
}

class Resource2 implements AutoCloseable {
	
protected String name;
	
	public Resource2(String name) {
		this.name = name;
	}
	
	public void doWork() {
		System.out.println("Worked " + name);
	}
	
	public void close() {
		System.out.println("Closed " + name);
	}
}

class Resource3 {
	
	protected String name;
	
	private Resource3(String name) {
		this.name = name;
	}
	
	public void doWork() {
		System.out.println("Worked " + name);
	}
	
	public void close() {
		System.out.println("Closed " + name);
	}
	
	public static void doWithResource(String name, Consumer<Resource3> consumer) {
		Resource3 res3 = new Resource3(name);
		consumer.accept(res3);
		res3.close();
	}
	
}

public class Demo4TryWithResource {

	public static void scopedRes1() {
		Resource1 res1 = new Resource1("res1");
		res1.doWork();
	}
	
	public static void main(String[] args) {
		scopedRes1();
		System.gc();
		
		try(Resource2 res2 = new Resource2("res2")) {
			res2.doWork();
		}
		
		Resource3.doWithResource("res3", (res3) -> {
			res3.doWork();
		});
	}
	
}
