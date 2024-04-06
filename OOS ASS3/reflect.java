class E{
}

// public class reflect{
// 	public static void main(String[] args){
// 		Class c = (new E()).getClass();
// 		System.out.println(int.TYPE);
// 	}
// }

class GetClassInfo {
	public static void main(String args[]) throws Exception {
	Class<?> c = Class.forName("java.lang.Integer");
	System.out.println("Cannonical name: "+c.getCanonicalName());
	System.out.println("Simple name: "+c.getSimpleName());
	Package p = c.getPackage();
	System.out.println("Package name: "+p.getName());
	System.out.println("Is synthetic class: "+c.isSynthetic());
	System.out.println("Protection domain: "+c.getProtectionDomain());
	}
}
