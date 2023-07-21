package jpql;

import javax.persistence.*;
import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//-----------------------------------------------------------
            // 벌크 연산
            Team team1 = new Team();
            team1.setName("팀A");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("팀B");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(team1);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원2");
            member3.setTeam(team2);
            em.persist(member3);

            //FLUSH가 되면서 위에 쌓여있던 SQL문들이 실행된다.
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            //33333333333333333333333333
            em.clear();

            //2222222222222222222222
            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember.getAge() = " + findMember.getAge());
            // 위에 처럼 해도 벌크는 DB에 반영되고 find는 영속성 컨텍스트에서 가져오기 때문에 그대로 0이다.

            // 1111111111111111111111111
            System.out.println("resultCount = " + resultCount);

            System.out.println("member1.getAge() = " + member1.getAge());
            System.out.println("member2.getAge() = " + member2.getAge());
            System.out.println("member3.getAge() = " + member3.getAge());
//          애플리케이션에는 0 살 DB에는 20살

            // 벌크 연산 - 1
//            Team team1 = new Team();
//            team1.setName("팀A");
//            em.persist(team1);
//
//            Team team2 = new Team();
//            team2.setName("팀B");
//            em.persist(team2);
//
//            Member member1 = new Member();
//            member1.setUsername("회원1");
//            member1.setTeam(team1);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("회원2");
//            member2.setTeam(team1);
//            em.persist(member2);
//
//            Member member3 = new Member();
//            member3.setUsername("회원2");
//            member3.setTeam(team2);
//            em.persist(member3);
//
//            em.flush();
//            em.clear();
//
//            int resultCount = em.createQuery("update Member m set m.age = 20")
//                    .executeUpdate();
//
//            System.out.println("resultCount = " + resultCount);

            //-------------------------------------------------------
            // Named 쿼리
//            Team team1 = new Team();
//            team1.setName("팀A");
//            em.persist(team1);
//
//            Team team2 = new Team();
//            team2.setName("팀B");
//            em.persist(team2);
//
//            Member member1 = new Member();
//            member1.setUsername("회원1");
//            member1.setTeam(team1);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("회원2");
//            member2.setTeam(team1);
//            em.persist(member2);
//
//            Member member3 = new Member();
//            member3.setUsername("회원2");
//            member3.setTeam(team2);
//            em.persist(member3);
//
//            em.flush();
//            em.clear();
//
//            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
//                    .setParameter("username", "회원1")
//                    .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member = " + member);
//            }

            //-----------------------------------------------------------------
            // 엔티티 직접 사용
//            Team team1 = new Team();
//            team1.setName("팀A");
//            em.persist(team1);
//
//            Team team2 = new Team();
//            team2.setName("팀B");
//            em.persist(team2);
//
//            Member member1 = new Member();
//            member1.setUsername("회원1");
//            member1.setTeam(team1);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("회원2");
//            member2.setTeam(team1);
//            em.persist(member2);
//
//            Member member3 = new Member();
//            member3.setUsername("회원2");
//            member3.setTeam(team2);
//            em.persist(member3);
//
//            em.flush();
//            em.clear();
//
//            //외래 키 값 사용
//            String query = "select m from Member m where m.team = :team";
//
//            List<Member> members = em.createQuery(query, Member.class)
//                    .setParameter("team", team1)
//                    .getResultList();
//
//            for (Member member : members) {
//                System.out.println("member = " + member);
//            }

            //엔티티 직접 사용 2가지
            //첫번째
//            String query = "select m from Member m where m.id = :memberId";
//
//            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("memberId", member1.getId())
//                    .getSingleResult();
//
//            System.out.println("findMember = " + findMember);

            // 두번째
//            String query = "select m from Member m where m = :member";
//
//            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("member", member1)
//                    .getSingleResult();
//
//            System.out.println("findMember = " + findMember);

            //여러가지 조인
//            String query = "select t From Team t join t.members";
//            String query = "select t From Team t join fetch t.members";
//            String query = "select distinct t From Team t join fetch t.members";
//
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();
//
//            System.out.println("result.size() = " + result.size());
//
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + "| members = " + team.getMembers().size());
//                for (Member member : team.getMembers()) {
//                    System.out.println("-> member = " + member);
//                }
//            }


            //--------------------------------------------------------------------------------
////페치 조인
//            Team team1 = new Team();
//            team1.setName("팀A");
//            em.persist(team1);
//
//            Team team2 = new Team();
//            team2.setName("팀B");
//            em.persist(team2);
//
//            Member member1 = new Member();
//            member1.setUsername("회원1");
//            member1.setTeam(team1);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("회원2");
//            member2.setTeam(team1);
//            em.persist(member2);
//
//            Member member3 = new Member();
//            member3.setUsername("회원3");
//            member3.setTeam(team2);
//            em.persist(member3);
//
//            em.flush();
//            em.clear();
//
////            //컬렉션 패치 조인
//            String query = "select t From Team t join t.members";
//            // distinct 추가해보던지 말던지~~~
//            System.out.println("===========================================111");
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();
//
//            System.out.println("result = " + result.size());
//
//            System.out.println("===========================================222");
//            for (Team team : result) {
//                System.out.println("===========================================333");
//                System.out.println("team = " + team.getName() + "| members = " + team.getMembers().size());
//                System.out.println("===========================================444");
//                for (Member member : team.getMembers()) {
//                    System.out.println("-> member = " + member);
//                }
//            }

//            String query = "select t From Team t";
//
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();
//
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + "| members = " + team.getMembers().size());
//                for (Member member : team.getMembers()) {
//                    System.out.println("-> member = " + member);
//                }
//            }

            // 일반 페치 조인
//            String query = "select m From Member m join fetch m.team";
//
////            String query = "select m From Member m";
//
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
//            }



            // ---------------------------------------------------------------------
            // 경로 표현식
//            Team team = new Team();
//            em.persist(team);
//
//            Member member1 = new Member();
//            member1.setUsername("관리자1");
//            member1.setTeam(team);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("관리자2");
//            member2.setTeam(team);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();


// join을 통해 별칭을 얻으면 별칭을 통해 탐색이 가능하다. 기본적으로는 탐색이 불가능하다.
//            String query = "select m.username From Team t join t.members m";

            // 컬렉션 값 연관 경로
//            String query = "select t.members From Team t";
//
//            List<Collection> result = em.createQuery(query, Collection.class)
//                    .getResultList();
//
//            System.out.println("result = " + result);

            // 컬렉션 값 연관 경로
//            String query = "select t.members.size From Team t";
//
//            Integer result = em.createQuery(query, Integer.class)
//                    .getSingleResult();
//
//            System.out.println("result = " + result);

            // 단일 값 연관 경로, 묵시적 내부 조인 발생
//            String query = "select m.team.name From Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            // 상태 필드
//            String query = "select m.username From Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            // --------------------------------------------------------------------------
            // JPQL 함수
//            Member member1 = new Member();
//            member1.setUsername("관리자1");
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("관리자2");
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            // 사용자 정의 함수 사용
//            String query = "select function('group_concat', m.username) From Member m";
//
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            // size 사용
//            String query = "select size(t.members) From Team t";
//
//            List<Integer> result = em.createQuery(query, Integer.class)
//                    .getResultList();
//
//            for (Integer s : result) {
//                System.out.println("s = " + s);
//            }

            //----------------------------------------------------------------
// locate 사용
//            String query = "select locate('de','abcdefg') From Member m";
//
//            List<Integer> result = em.createQuery(query, Integer.class)
//                    .getResultList();
//
//            for (Integer s : result) {
//                System.out.println("s = " + s);
//            }

            // concat 사용
//            String query = "select concat('a', 'b') From Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            // subString 사용
//            String query = "select substring(m.username, 2,3) From Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }
            // ------------------------------------------------------------------------------------------------
            // 조건식
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("관리자");
//            member.setAge(10);
//            member.setTeam(team);
//            member.setType(MemberType.ADMIN);
//            em.persist(member);
//
//            em.flush();
//            em.clear();

// NULLIF
//            String query = "select nullif(m.username, '관리자') from Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            // coalesce
//            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

//             기본 case문
//            String query = "select " +
//                    "case when m.age <= 10 then '학생요금' " +
//                    "     when m.age >= 60 then '경로요금' " +
//                    "     else '일반요금' " +
//                    "end " +
//                    "from Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }
            //--------------------------------------------------------------------------------------------
            // 타입 표현과 기타식
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("teamA");
//            member.setAge(10);
//            member.setTeam(team);
//            member.setType(MemberType.ADMIN);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            String query1 = "select m.username, 'HELLO', TRUE From Member m " +
//                            "where m.type = :userType";
//            List<Object[]> result1 = em.createQuery(query1)
//                    .setParameter("userType", MemberType.ADMIN)
//                    .getResultList();
//
//            for (Object[] objects : result1) {
//                System.out.println("objects = " + objects[0]);
//                System.out.println("objects = " + objects[1]);
//                System.out.println("objects = " + objects[2]);
//            }
            //---------------------------------------------------------------------------------------------
            // 조인
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("teamA");
//            member.setAge(10);
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            // 연관관계가 없는 엔티티 외부 조인
//            String query1 = "select m from Member m left join Team t on m.username = t.name";
//            List<Member> result1 = em.createQuery(query1, Member.class)
//                    .getResultList();
//            System.out.println("result = " + result1.size());
//
//            String query2 = "select m from Member m left join m.team t on t.name = 'teamA'"; // 원래는 파라미터 바인딩 해야한다.
//            List<Member> result2 = em.createQuery(query2, Member.class)
//                    .getResultList();
//            System.out.println("result = " + result2.size());


//            // 세터 조인
//            String query = "select m from Member m, Team t where m.username = t.name";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();
//            System.out.println("result = " + result.size());


            // left
//            String query = "select m from Member m left join m.team";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();

            // inner
//            String query = "select m from Member m inner join m.team t";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();

            //---------------------------------------------------------------------------------------------
            //페이징
//            for (int i = 0; i < 100; i++) {
//                Member member = new Member();
//                member.setUsername("member" + i);
//                member.setAge(i);
//                em.persist(member);
//            }
//
//            em.flush();
//            em.clear();
//
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println("result.size() = " + result.size());
//            for (Member member1 : result) {
//                System.out.println("member1 = " + member1);
//            }
//---------------------------------------------------------------------------------------------------------
// 가장 선호하는 방법 MemberDTO 사용하기
//            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//
//            MemberDTO memberDTO = result.get(0);
//            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
//            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());

            // 스칼라 프로잭션 제네릭스 사용
//            List<Object[]> resultList = em.createQuery("select m.username, m.age from Member m")
//                    .getResultList();
//
//            Object[] result = resultList.get(0);
//            System.out.println("username = " + result[0]);
//            System.out.println("age = " + result[1]);

            // 스칼라 프로잭션
//            List resultList = em.createQuery("select m.username, m.age from Member m")
//                    .getResultList();
//
//            Object o = resultList.get(0);
//            Object[] result = (Object[]) o;
//            System.out.println("username = " + result[0]);
//            System.out.println("age = " + result[1]);

            // 임베디드 타입 프로잭션
//            em.createQuery("select o.address from Order o", Address.class)
//                    .getResultList();

            // 엔티티 프로잭션 2
//            List<Team> result = em.createQuery("select m.team from Member m join m.team t", Team.class)
//                    .getResultList();

            //엔티티 프로잭션
//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();

//            Member findMember = result.get(0);
//            findMember.setAge(20);

            // 파라미터 바인딩
//            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
//                    .setParameter("username", "member1")
//                    .getSingleResult();
//            System.out.println("result = " + result.getUsername());

            // chain으로 묶어서 깔끔하게 사용한다.
//            TypedQuery<Member> query1 = em.createQuery("select m from Member m where m.username = :username", Member.class);
//            query1.setParameter("username", "member1");
//            Member result = query1.getSingleResult();
//            System.out.println("result = " + result.getUsername());

            // 하나만 가져올 때, 진짜 결과가 하나일 때만 사용해야 한다.
//            Member result = query1.getSingleResult();
//            System.out.println("result = " + result);

            // 리스트로 여러개를 가져올 때
//            List<Member> resultList = query1.getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

            //타입 별 명시
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
//            Query query3 = em.createQuery("select m.username, m.age from Member m", String.class);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
