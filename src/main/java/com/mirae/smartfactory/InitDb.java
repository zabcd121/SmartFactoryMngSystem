package com.mirae.smartfactory;

import com.mirae.smartfactory.domain.model.billet.Billet;
import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.domain.model.process.casting.Casting;
import com.mirae.smartfactory.domain.model.process.casting.CastingData;
import com.mirae.smartfactory.domain.model.process.casting.CastingPreparation;
import com.mirae.smartfactory.domain.model.process.casting.CastingTemperature;
import com.mirae.smartfactory.domain.model.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.model.process.furnace.Ingredient;
import com.mirae.smartfactory.domain.model.resource.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
        initService.dbInit4();
        initService.dbInit5();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
//        private final BCryptPasswordEncoder bCryptPasswordEncoder;

        public void dbInit1() {
            Member member = Member.createMember("김현석", Title.REPRESENTATIVE, RoleType.ROLE_MEMBER, "abcd123", "1234");
            Member adminMember1 = Member.createMember("관리자1", Title.REPRESENTATIVE, RoleType.ROLE_ADMIN, "admin1234", "1234");
            Member adminMember2 = Member.createMember("관리자2", Title.REPRESENTATIVE, RoleType.ROLE_ADMIN, "admin2", "1234");
            em.persist(member);
            em.persist(adminMember1);
            em.persist(adminMember2);

            Process process = Process.createProcess(LocalDate.now(), 1, 1, 6603, 7, member);

            List<Material> materials = new ArrayList<>();
            Material material1 = Material.createMaterial(ResourceType.INGOT, 1, "인코드", 12113, process);
            Material material2 = Material.createMaterial(ResourceType.SCRAP, 2, "자체", 1500, process);
            Material material3 = Material.createMaterial(ResourceType.OUTER, 3,"마대", 900, process);
            Material material4 = Material.createMaterial(ResourceType.OUTER, 4, "재괴", 1000, process);
            Material material5 = Material.createMaterial(ResourceType.OUTER, 5, "밴딩", 6500, process);
            materials.add(material1);
            materials.add(material2);
            materials.add(material3);
            materials.add(material4);
            materials.add(material5);


            em.persist(material1);
            em.persist(material2);
            em.persist(material3);
            em.persist(material4);
            em.persist(material5);


            List<Additive> additives = new ArrayList<>();
            Additive additive1 = Additive.createAdditive(1, "원석", 30, process);
            Additive additive2 = Additive.createAdditive(2, "모합금", 73, process);
            Additive additive3 = Additive.createAdditive(3, "MG", 71, process);
            additives.add(additive1);
            additives.add(additive2);
            additives.add(additive3);

            em.persist(additive1);
            em.persist(additive2);
            em.persist(additive3);

            

            FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcess(LocalTime.now(), LocalTime.now(), LocalTime.now(),
                    20, 10, 900, null, null, "금오공대", process);

            List<Ingredient> ingredients = new ArrayList<>();
            Ingredient ingredient1 = Ingredient.createIngredient(furnaceProcess,1, "Fe", 1.333f);
            Ingredient ingredient2 = Ingredient.createIngredient(furnaceProcess,2, "Cu", 1.333f);
            Ingredient ingredient3 = Ingredient.createIngredient(furnaceProcess,3, "Zn", 1.333f);
            Ingredient ingredient4 = Ingredient.createIngredient(furnaceProcess,4, "Mn", 1.333f);
            Ingredient ingredient5 = Ingredient.createIngredient(furnaceProcess,5, "Si", 1.333f);
            Ingredient ingredient6 = Ingredient.createIngredient(furnaceProcess,6, "Mg", 1.333f);
            ingredients.add(ingredient1);
            ingredients.add(ingredient2);
            ingredients.add(ingredient3);
            ingredients.add(ingredient4);
            ingredients.add(ingredient5);
            ingredients.add(ingredient6);

            em.persist(ingredient1);
            em.persist(ingredient2);
            em.persist(ingredient3);
            em.persist(ingredient4);
            em.persist(ingredient5);
            em.persist(ingredient6);
            
            em.persist(furnaceProcess);

            CastingPreparation castingPreparation = CastingPreparation.createCastingPreparation(1, 1, 1, 1, 1, 1, 1, "테스트1");
            CastingData castingData = CastingData.createCastingData(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1);
            CastingTemperature castingTemperature = CastingTemperature.createCastingTemperature(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Billet billet = Billet.createBillet(213L, 1, 1, "billetMore테스트");
            Casting casting = Casting.createCasting(LocalTime.now(), LocalTime.now(), "강수성", "이충엽", "특이사항1", process, castingPreparation, castingData, castingTemperature, billet);

            em.persist(casting);

            ResourceName resourceName1 = ResourceName.createResourceName(ResourceType.OUTER, "뒷판");
            ResourceName resourceName2 = ResourceName.createResourceName(ResourceType.OUTER, "마대");
            ResourceName resourceName3 = ResourceName.createResourceName(ResourceType.OUTER, "재괴");
            ResourceName resourceName4 = ResourceName.createResourceName(ResourceType.OUTER, "상, 동납");
            ResourceName resourceName5 = ResourceName.createResourceName(ResourceType.SI, "원석");
            ResourceName resourceName6 = ResourceName.createResourceName(ResourceType.SI, "모합금");
            ResourceName resourceName7 = ResourceName.createResourceName(ResourceType.SI, "MG");
            ResourceName resourceName8 = ResourceName.createResourceName(ResourceType.INGREDIENT, "Fe");
            ResourceName resourceName9 = ResourceName.createResourceName(ResourceType.INGREDIENT, "Cu");
            ResourceName resourceName10 = ResourceName.createResourceName(ResourceType.INGREDIENT, "Zn");
            ResourceName resourceName11 = ResourceName.createResourceName(ResourceType.INGREDIENT, "Mn");
            ResourceName resourceName12 = ResourceName.createResourceName(ResourceType.INGREDIENT, "SI");
            ResourceName resourceName13 = ResourceName.createResourceName(ResourceType.INGREDIENT, "Mg");
            ResourceName resourceName14 = ResourceName.createResourceName(ResourceType.INGREDIENT, "Mn");
            ResourceName resourceName15 = ResourceName.createResourceName(ResourceType.BUSINESS_CONTACT, "금오상사");
            ResourceName resourceName16 = ResourceName.createResourceName(ResourceType.BUSINESS_CONTACT, "구미상사");
            em.persist(resourceName1);
            em.persist(resourceName2);
            em.persist(resourceName3);
            em.persist(resourceName4);
            em.persist(resourceName5);
            em.persist(resourceName6);
            em.persist(resourceName7);
            em.persist(resourceName8);
            em.persist(resourceName9);
            em.persist(resourceName10);
            em.persist(resourceName11);
            em.persist(resourceName12);
            em.persist(resourceName13);
            em.persist(resourceName14);
            em.persist(resourceName15);
            em.persist(resourceName16);
        }

        public void dbInit2() {
            Member member = Member.createMember("강수성", Title.REPRESENTATIVE, RoleType.ROLE_MEMBER, "kang123", "1234");
//            Member member = Member.createMember("강수성", Title.REPRESENTATIVE, RoleType.ROLE_MEMBER, "kang123", bCryptPasswordEncoder.encode("1234"));
            em.persist(member);

            Process process = Process.createProcess(LocalDate.now(), 2, 2, 6803, 7, member);

            List<Material> materials = new ArrayList<>();
            Material material1 = Material.createMaterial(ResourceType.INGOT, 1, "인코드", 14012, process);
            Material material2 = Material.createMaterial(ResourceType.SCRAP, 2, "자체", 12311, process);
            Material material3 = Material.createMaterial(ResourceType.OUTER, 3, "마대", 4123, process);
            Material material4 = Material.createMaterial(ResourceType.OUTER, 4, "재괴", 5312, process);
            Material material5 = Material.createMaterial(ResourceType.OUTER, 5, "밴딩", 13124, process);
            materials.add(material1);
            materials.add(material2);
            materials.add(material3);
            materials.add(material4);
            materials.add(material5);


            em.persist(material1);
            em.persist(material2);
            em.persist(material3);
            em.persist(material4);
            em.persist(material5);


            List<Additive> additives = new ArrayList<>();
            Additive additive1 = Additive.createAdditive(1, "원석", 54, process);
            Additive additive2 = Additive.createAdditive(2, "모합금", 91, process);
            Additive additive3 = Additive.createAdditive(3, "MG", 153, process);
            additives.add(additive1);
            additives.add(additive2);
            additives.add(additive3);

            em.persist(additive1);
            em.persist(additive2);
            em.persist(additive3);

            FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcess(LocalTime.now(), LocalTime.now(), LocalTime.now(),
                    12, 22, 1200, null, null, "컴소공", process);

            List<Ingredient> ingredients = new ArrayList<>();
            Ingredient ingredient1 = Ingredient.createIngredient(furnaceProcess,1, "Fe", 5.123f);
            Ingredient ingredient2 = Ingredient.createIngredient(furnaceProcess,2, "Cu", 1.231f);
            Ingredient ingredient3 = Ingredient.createIngredient(furnaceProcess,3, "Zn", 3.311f);
            Ingredient ingredient4 = Ingredient.createIngredient(furnaceProcess,4, "Mn", 0.912f);
            Ingredient ingredient5 = Ingredient.createIngredient(furnaceProcess,5, "Si", 1.333f);
            Ingredient ingredient6 = Ingredient.createIngredient(furnaceProcess,6, "Mg", 2.512f);
            ingredients.add(ingredient1);
            ingredients.add(ingredient2);
            ingredients.add(ingredient3);
            ingredients.add(ingredient4);
            ingredients.add(ingredient5);
            ingredients.add(ingredient6);

            em.persist(ingredient1);
            em.persist(ingredient2);
            em.persist(ingredient3);
            em.persist(ingredient4);
            em.persist(ingredient5);
            em.persist(ingredient6);

            em.persist(furnaceProcess);


            CastingPreparation castingPreparation = CastingPreparation.createCastingPreparation(1, 1, 1, 1, 1, 1, 1, "테스트2");
            CastingData castingData = CastingData.createCastingData(2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 21, 12, 1, 1, 1, 1);
            CastingTemperature castingTemperature = CastingTemperature.createCastingTemperature(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Billet billet = Billet.createBillet(1234L, 2, 2, "billetMore테스트2");
            Casting casting = Casting.createCasting(LocalTime.now(), LocalTime.now(), "김현석", "박형준","특이사항2", process, castingPreparation, castingData, castingTemperature, billet);

            em.persist(casting);


        }
        public void dbInit3() {
            Member member = Member.createMember("member1", Title.REPRESENTATIVE, RoleType.ROLE_MEMBER, "member1", "1234");
//            Member member = Member.createMember("강수성", Title.REPRESENTATIVE, RoleType.ROLE_MEMBER, "kang123", bCryptPasswordEncoder.encode("1234"));
            em.persist(member);

            Process process = Process.createProcess(LocalDate.now().minusMonths(1), 1, 1, 6712, 8, member);

            List<Material> materials = new ArrayList<>();
            Material material1 = Material.createMaterial(ResourceType.INGOT, 1, "인코드", 31234, process);
            Material material2 = Material.createMaterial(ResourceType.SCRAP, 2, "자체", 523, process);
            Material material3 = Material.createMaterial(ResourceType.OUTER, 3, "마대", 1234, process);
            Material material4 = Material.createMaterial(ResourceType.OUTER, 4, "재괴", 7123, process);
            Material material5 = Material.createMaterial(ResourceType.OUTER, 5, "밴딩", 1236, process);
            materials.add(material1);
            materials.add(material2);
            materials.add(material3);
            materials.add(material4);
            materials.add(material5);


            em.persist(material1);
            em.persist(material2);
            em.persist(material3);
            em.persist(material4);
            em.persist(material5);


            List<Additive> additives = new ArrayList<>();
            Additive additive1 = Additive.createAdditive(1, "원석", 31, process);
            Additive additive2 = Additive.createAdditive(2, "모합금", 123, process);
            Additive additive3 = Additive.createAdditive(3, "MG", 76, process);
            additives.add(additive1);
            additives.add(additive2);
            additives.add(additive3);

            em.persist(additive1);
            em.persist(additive2);
            em.persist(additive3);


            FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcess(LocalTime.now(), LocalTime.now(), LocalTime.now(),
                    12, 22, 800, null, null, "컴소공", process);

            List<Ingredient> ingredients = new ArrayList<>();
            Ingredient ingredient1 = Ingredient.createIngredient(furnaceProcess,1, "Fe", 2.512f);
            Ingredient ingredient2 = Ingredient.createIngredient(furnaceProcess,2, "Cu", 2.512f);
            Ingredient ingredient3 = Ingredient.createIngredient(furnaceProcess,3, "Zn", 2.512f);
            Ingredient ingredient4 = Ingredient.createIngredient(furnaceProcess,4, "Mn", 2.512f);
            Ingredient ingredient5 = Ingredient.createIngredient(furnaceProcess,5, "Si", 2.512f);
            Ingredient ingredient6 = Ingredient.createIngredient(furnaceProcess,6, "Mg", 2.512f);
            ingredients.add(ingredient1);
            ingredients.add(ingredient2);
            ingredients.add(ingredient3);
            ingredients.add(ingredient4);
            ingredients.add(ingredient5);
            ingredients.add(ingredient6);

            em.persist(ingredient1);
            em.persist(ingredient2);
            em.persist(ingredient3);
            em.persist(ingredient4);
            em.persist(ingredient5);
            em.persist(ingredient6);

            em.persist(furnaceProcess);


            CastingPreparation castingPreparation = CastingPreparation.createCastingPreparation(1, 1, 1, 1, 1, 1, 1, "테스트2");
            CastingData castingData = CastingData.createCastingData(2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 21, 12, 1, 1, 1, 1);
            CastingTemperature castingTemperature = CastingTemperature.createCastingTemperature(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Billet billet = Billet.createBillet(8123L, 2, 2, "billetMore테스트2");
            Casting casting = Casting.createCasting(LocalTime.now(), LocalTime.now(), "김현석", "박형준","특이사항2", process, castingPreparation, castingData, castingTemperature, billet);

            em.persist(casting);


        }

        public void dbInit4() {
            Member member = Member.createMember("member2", Title.REPRESENTATIVE, RoleType.ROLE_MEMBER, "member2", "1234");
//            Member member = Member.createMember("강수성", Title.REPRESENTATIVE, RoleType.ROLE_MEMBER, "kang123", bCryptPasswordEncoder.encode("1234"));
            em.persist(member);

            Process process = Process.createProcess(LocalDate.now().minusDays(1), 1, 1, 6712, 8, member);

            List<Material> materials = new ArrayList<>();
            Material material1 = Material.createMaterial(ResourceType.INGOT, 1, "인코드", 31234, process);
            Material material2 = Material.createMaterial(ResourceType.SCRAP, 2, "자체", 523, process);
            Material material3 = Material.createMaterial(ResourceType.OUTER, 3,"마대", 1234, process);
            Material material4 = Material.createMaterial(ResourceType.OUTER, 4,"재괴", 7123, process);
            Material material5 = Material.createMaterial(ResourceType.OUTER, 5,"밴딩", 1236, process);
            materials.add(material1);
            materials.add(material2);
            materials.add(material3);
            materials.add(material4);
            materials.add(material5);

            em.persist(material1);
            em.persist(material2);
            em.persist(material3);
            em.persist(material4);
            em.persist(material5);


            List<Additive> additives = new ArrayList<>();
            Additive additive1 = Additive.createAdditive(1, "원석", 1234, process);
            Additive additive2 = Additive.createAdditive(2, "모합금", 41, process);
            Additive additive3 = Additive.createAdditive(3, "MG", 123, process);
            additives.add(additive1);
            additives.add(additive2);
            additives.add(additive3);

            em.persist(additive1);
            em.persist(additive2);
            em.persist(additive3);

            FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcess(LocalTime.now(), LocalTime.now(), LocalTime.now(),
                    12, 22, 500, null, null, "컴소공", process);

            List<Ingredient> ingredients = new ArrayList<>();
            Ingredient ingredient1 = Ingredient.createIngredient(furnaceProcess,1, "Fe", 3.512f);
            Ingredient ingredient2 = Ingredient.createIngredient(furnaceProcess,2, "Cu", 2.512f);
            Ingredient ingredient3 = Ingredient.createIngredient(furnaceProcess,3, "Zn", 3.512f);
            Ingredient ingredient4 = Ingredient.createIngredient(furnaceProcess,4, "Mn", 2.512f);
            Ingredient ingredient5 = Ingredient.createIngredient(furnaceProcess,5, "Si", 3.512f);
            Ingredient ingredient6 = Ingredient.createIngredient(furnaceProcess,6, "Mg", 2.512f);
            ingredients.add(ingredient1);
            ingredients.add(ingredient2);
            ingredients.add(ingredient3);
            ingredients.add(ingredient4);
            ingredients.add(ingredient5);
            ingredients.add(ingredient6);

            em.persist(ingredient1);
            em.persist(ingredient2);
            em.persist(ingredient3);
            em.persist(ingredient4);
            em.persist(ingredient5);
            em.persist(ingredient6);

            em.persist(furnaceProcess);


            CastingPreparation castingPreparation = CastingPreparation.createCastingPreparation(1, 1, 1, 1, 1, 1, 1, "테스트2");
            CastingData castingData = CastingData.createCastingData(2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 21, 12, 1, 1, 1, 1);
            CastingTemperature castingTemperature = CastingTemperature.createCastingTemperature(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Billet billet = Billet.createBillet(4121L, 2, 2, "billetMore테스트2");
            Casting casting = Casting.createCasting(LocalTime.now(), LocalTime.now(), "김현석", "박형준","특이사항2", process, castingPreparation, castingData, castingTemperature, billet);

            em.persist(casting);


        }

        public void dbInit5() {
            Member member = Member.createMember("member3", Title.REPRESENTATIVE, RoleType.ROLE_MEMBER, "member3", "1234");
//            Member member = Member.createMember("강수성", Title.REPRESENTATIVE, RoleType.ROLE_MEMBER, "kang123", bCryptPasswordEncoder.encode("1234"));
            em.persist(member);

            Process process = Process.createProcess(LocalDate.now().minusDays(2), 1, 1, 6712, 8, member);

            List<Material> materials = new ArrayList<>();
            Material material1 = Material.createMaterial(ResourceType.INGOT, 1, "인코드", 4211, process);
            Material material2 = Material.createMaterial(ResourceType.SCRAP, 2, "자체", 2311, process);
            Material material3 = Material.createMaterial(ResourceType.OUTER, 3,"마대", 5311, process);
            Material material4 = Material.createMaterial(ResourceType.OUTER, 4,"재괴", 3123, process);
            Material material5 = Material.createMaterial(ResourceType.OUTER, 5,"밴딩", 1244, process);
            materials.add(material1);
            materials.add(material2);
            materials.add(material3);
            materials.add(material4);
            materials.add(material5);

            em.persist(material1);
            em.persist(material2);
            em.persist(material3);
            em.persist(material4);
            em.persist(material5);


            List<Additive> additives = new ArrayList<>();
            Additive additive1 = Additive.createAdditive(1, "원석", 51, process);
            Additive additive2 = Additive.createAdditive(2, "모합금", 51, process);
            Additive additive3 = Additive.createAdditive(3, "MG", 75, process);
            additives.add(additive1);
            additives.add(additive2);
            additives.add(additive3);

            em.persist(additive1);
            em.persist(additive2);
            em.persist(additive3);

            FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcess(LocalTime.now(), LocalTime.now(), LocalTime.now(),
                    12, 22, 300, null, null, "컴소공", process);

            List<Ingredient> ingredients = new ArrayList<>();
            Ingredient ingredient1 = Ingredient.createIngredient(furnaceProcess,1, "Fe", 1.852f);
            Ingredient ingredient2 = Ingredient.createIngredient(furnaceProcess,2, "Cu", 2.512f);
            Ingredient ingredient3 = Ingredient.createIngredient(furnaceProcess,3, "Zn", 3.512f);
            Ingredient ingredient4 = Ingredient.createIngredient(furnaceProcess,4, "Mn", 1.852f);
            Ingredient ingredient5 = Ingredient.createIngredient(furnaceProcess,5, "Si", 3.512f);
            Ingredient ingredient6 = Ingredient.createIngredient(furnaceProcess,6, "Mg", 1.852f);
            ingredients.add(ingredient1);
            ingredients.add(ingredient2);
            ingredients.add(ingredient3);
            ingredients.add(ingredient4);
            ingredients.add(ingredient5);
            ingredients.add(ingredient6);

            em.persist(ingredient1);
            em.persist(ingredient2);
            em.persist(ingredient3);
            em.persist(ingredient4);
            em.persist(ingredient5);
            em.persist(ingredient6);

            em.persist(furnaceProcess);


            CastingPreparation castingPreparation = CastingPreparation.createCastingPreparation(1, 1, 1, 1, 1, 1, 1, "테스트2");
            CastingData castingData = CastingData.createCastingData(2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 21, 12, 1, 1, 1, 1);
            CastingTemperature castingTemperature = CastingTemperature.createCastingTemperature(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Billet billet = Billet.createBillet(6132L, 2, 2, "billetMore테스트2");
            Casting casting = Casting.createCasting(LocalTime.now(), LocalTime.now(), "김현석", "박형준","특이사항2", process, castingPreparation, castingData, castingTemperature, billet);

            em.persist(casting);


        }
    }




}
