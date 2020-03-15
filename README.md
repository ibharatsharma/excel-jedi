# Excel Jedi
Excel Jedi is a basic utility to:
1. Write an ArrayList to an excel file
1. Read an excel file into a list of Java object

# TODO:
1. Check if file exists. If Yes, then should we delete old file? or give user a flag in the writer API to decide that?
1. Extend API to provide styling customisations

# Limitations

# Usage

```
public class TestClient {
	
	public static void main(String[] args) throws Exception {
		
		List<Employee> emps = new ArrayList<>();
		Employee e1 = new Employee();
		e1.setName("John");
		e1.setAge(25);
		e1.setSalary(1000.00);
		emps.add(e1);
		
		ExcelTemplate<Employee> t = new SimpleExcelTemplate<>();
		t.writeExcel(emps, "C:\\temp\\employees.xlsx");
	}
}
```


# Build
Uses maven 3.5+ as build tool. From project's root directory run following maven command:

```
mvn clean install
```

# Maven Dependency
```
<dependency>
	<groupId>com.basicutils</groupId>
	<artifactId>excel-jedi</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```