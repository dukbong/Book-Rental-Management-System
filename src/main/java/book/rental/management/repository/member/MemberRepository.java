package book.rental.management.repository.member;

import book.rental.management.domain.member.Member;
import book.rental.management.repository.member.mybatis.MemberMybatisRepository;
import book.rental.management.repository.member.querydsl.MemberQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDslRepository, MemberMybatisRepository {
    Optional<Member> findByName(String name);
    List<Member> findAllByOrderByNameAsc();
}
