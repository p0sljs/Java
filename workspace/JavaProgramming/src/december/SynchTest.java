package december;

public class SynchTest {
	public static void main(String[] args) {
		SyncObject obj = new SyncObject();

		Thread th1 = new WorkerThread("kitae", obj);
		Thread th2 = new WorkerThread("hyosoo", obj);

		th1.start();
		th2.start();
	}
}

class SyncObject {
	int sum = 0;

	synchronized void add() {
		int n = sum;
		n += 10;
		sum = n;
	}

	int getSum() {
		return sum;
	}
}

class WorkerThread extends Thread {
	SyncObject sObj;

	public WorkerThread(String name, SyncObject sObj) {
		super(name);
		this.sObj = sObj;
	}

	public void run() {
		int i = 0;

		while (i < 100000) {
			sObj.add();
			i++;
		}
		String name = Thread.currentThread().getName();
		System.out.println(name + " : " + sObj.sum);
	}
}