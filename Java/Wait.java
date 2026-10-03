class Wait {
	public static void main(String[] args) throws InterruptedException {
		if (args.length != 1) {
			System.out.println("Usage: java Wait <time>");
			System.exit(1);
		}

		int time = Integer.parseInt(args[0]) * 1000;
		System.out.println("Sleeping for " + time + " seconds");
		Thread.sleep(time);
		System.out.println("Awake!");
	}
}