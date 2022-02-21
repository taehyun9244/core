package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountPolicy.class);

        DiscaountService discaountService = ac.getBean(DiscaountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discaountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discaountService).isInstanceOf(DiscaountService.class);
        assertThat(discountPrice).isEqualTo(1000);


        int reatDiscountPrice = discaountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(reatDiscountPrice).isEqualTo(2000);
    }

    static class DiscaountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscaountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }

}

