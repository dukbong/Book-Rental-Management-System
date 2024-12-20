package book.rental.management.repository.member.querydsl;

import book.rental.management.domain.member.Member;
import book.rental.management.domain.member.QMember;
import book.rental.management.dto.MemberCondition;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository {

    private final JPAQueryFactory query;

    public MemberQueryDslRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Member> getMemberByCondition(@NotNull MemberCondition condition) {
        QMember member = QMember.member;
        return query
                .select(member)
                .from(member)
                .where(
                        nameLike(condition.getName()),
                        emailLike(condition.getEmail()),
                        phoneNumberLike(condition.getPhoneNumber())
                )
                .orderBy(member.name.asc())
                .fetch();
    }

    private Predicate phoneNumberLike(String phoneNumber) {
        if(!StringUtils.hasText(phoneNumber)) {
            return null;
        }
        return QMember.member.phoneNumber.contains(phoneNumber);
    }

    private Predicate emailLike(String email) {
        if(!StringUtils.hasText(email)) {
            return null;
        }
        return QMember.member.email.contains(email);
    }

    private Predicate nameLike(String name) {
        if(!StringUtils.hasText(name)) {
            return null;
        }
        return QMember.member.name.contains(name);
    }

}
