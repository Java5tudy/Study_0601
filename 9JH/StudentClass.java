package Student;
import java.util.*;

class Student implements Comparable{
	String name;
	int ban, no;
	int kor, eng, math;
	int total, schoolRank, classRank;
	
	Student(String name, int ban, int no, int kor, int eng, int math){
		this.name = name;
		this.ban = ban;
		this.no = no;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		total = kor+eng+math;
	}
	int getTotal() { return total; }
	float getAverage() { return (int)((getTotal()/3f)*10+0.5)/10f; }
	public String toString () { 
		return name+","+ban+","+no+","+kor+","+eng+","+math+","
				+getTotal()+","+getAverage()+","+schoolRank+","+classRank;
	}
	public int compareTo(Object o) {
		if (o instanceof Student) {
			Student tmp = (Student)o;
			return tmp.total - this.total;
		} else { 
			return -1;
		}
	}
}
class BanNoAscending implements Comparator{
	public int compare(Object o1, Object o2) {
		Student s1 = (Student) o1;
		Student s2 = (Student) o2;
		int result = s1.ban - s2.ban;
		
		if(result==0) { return s1.no - s2.no;} 
			return result;
		}
}

class ClassTotalComparator implements Comparator{
	public int compare(Object o1, Object o2) {
		Student s1 = (Student)o1;
		Student s2 = (Student)o2;
		int result = s1.ban - s2.ban;
		if(result==0)
			result = s2.total - s1.total;
		return result;
	}
}

public class StudentClass {
	
	public static void calculateClassRank(List list) {
		Collections.sort(list, new ClassTotalComparator());
		int prevBan = -1;
		int prevRank = -1;
		int prevTotal = -1;
		int length = list.size();
		
		for (int i=0, n=0; i<length;i++, n++) {
			Student s = (Student)list.get(i);
			if(s.ban != prevBan) {
				prevRank = -1;
				prevTotal = -1;
				n=0;
			}
			if(s.total==prevTotal) {
				s.classRank = prevRank;
			} else {
				s.classRank = n+1;
			}
			prevBan = s.ban;
			prevRank = s.classRank;
			prevTotal = s.total;
		}
	}
	
	public static void calculateSchoolRank(List list) {
		Collections.sort(list, new BanNoAscending());

		int prevRank = -1;
		int prevTotal = -1;
		int length = list.size(); 
		
		for(int i=0; i<length; i++) {
			Student s = (Student)list.get(i);
			if(s.total==prevTotal){
				s.schoolRank = prevRank;
			} else {
				s.schoolRank = i+1;
			}
			prevRank = s.schoolRank;
			prevTotal = s.total;
		}
	}

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(new Student("홍바", 1, 1, 70, 90, 70));
		list.add(new Student("남바", 1, 2, 60, 100, 80));
		list.add(new Student("김바", 2, 3, 100, 100, 100));
		list.add(new Student("이바", 2, 1, 90, 70, 80));
		list.add(new Student("안바", 3, 2, 80, 80, 90));
		list.add(new Student("홍홍바", 2, 1, 70, 90, 70));
		list.add(new Student("남남바", 2, 2, 60, 100, 80));
		list.add(new Student("김김바", 1, 3, 100, 100, 100));
		list.add(new Student("이이바", 1, 2, 90, 70, 80));
		list.add(new Student("안안바", 3, 1, 80, 80, 90));

		calculateSchoolRank(list);
		calculateClassRank(list);
		
		Iterator it = list.iterator();
		
		while(it.hasNext())
			System.out.println(it.next());
	}

}
