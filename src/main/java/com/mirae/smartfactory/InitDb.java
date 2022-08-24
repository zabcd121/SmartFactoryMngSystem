package com.mirae.smartfactory;

import com.mirae.smartfactory.domain.billet.Billet;
import com.mirae.smartfactory.domain.process.Process;
import com.mirae.smartfactory.domain.process.casting.Casting;
import com.mirae.smartfactory.domain.process.casting.CastingData;
import com.mirae.smartfactory.domain.process.casting.CastingPreparation;
import com.mirae.smartfactory.domain.process.casting.CastingTemperature;
import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.process.furnace.Ingredient;
import com.mirae.smartfactory.domain.resource.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = Member.createMember("김현석", Title.REPRESENTATIVE, RoleType.MEMBER, "abcd123", "1234");
            em.persist(member);

            List<Material> materials = new ArrayList<>();
            Material material1 = Material.createMaterial(ResourceType.INGOT, "인코드", 12113);
            Material material2 = Material.createMaterial(ResourceType.SCRAP, "자체", 1500);
            Material material3 = Material.createMaterial(ResourceType.OUTER, "마대", 900);
            Material material4 = Material.createMaterial(ResourceType.OUTER, "재괴", 1000);
            Material material5 = Material.createMaterial(ResourceType.OUTER, "밴딩", 6500);
            materials.add(material1);
            materials.add(material2);
            materials.add(material3);
            materials.add(material4);
            materials.add(material5);


            List<Additive> additives = new ArrayList<>();
            Additive additive1 = Additive.createAdditive("원석", 30);
            Additive additive2 = Additive.createAdditive("모합금", 73);
            Additive additive3 = Additive.createAdditive("MG", 71);
            additives.add(additive1);
            additives.add(additive2);
            additives.add(additive3);

            List<Ingredient> ingredients = new ArrayList<>();
            Ingredient ingredient1 = Ingredient.createIngredient("Fe", 1.333f);
            Ingredient ingredient2 = Ingredient.createIngredient("Cu", 1.333f);
            Ingredient ingredient3 = Ingredient.createIngredient("Zn", 1.333f);
            Ingredient ingredient4 = Ingredient.createIngredient("Mn", 1.333f);
            Ingredient ingredient5 = Ingredient.createIngredient("Si", 1.333f);
            Ingredient ingredient6 = Ingredient.createIngredient("Mg", 1.333f);
            ingredients.add(ingredient1);
            ingredients.add(ingredient2);
            ingredients.add(ingredient3);
            ingredients.add(ingredient4);
            ingredients.add(ingredient5);
            ingredients.add(ingredient6);

            Process process = Process.createProcess(LocalDate.now(), 1, 1, 6603, 7, materials, additives, member);

            FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcess(LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),
                    20, 10, 900, null, null, "금오공대", process, ingredients);

            em.persist(furnaceProcess);

            CastingPreparation castingPreparation = CastingPreparation.createCastingPreparation(1, 1, 1, 1, 1, 1, 1, "테스트1");
            CastingData castingData = CastingData.createCastingData(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1);
            CastingTemperature castingTemperature = CastingTemperature.createCastingTemperature(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Billet billet = Billet.createBillet(213L, 1, 1, "billetMore테스트");
            Casting casting = Casting.createCasting(LocalDateTime.now(), LocalDateTime.now(), "강수성", "이충엽", "특이사항1", process, castingPreparation, castingData, castingTemperature, billet);

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
            Member member = Member.createMember("강수성", Title.REPRESENTATIVE, RoleType.MEMBER, "kang123", "1234");
            em.persist(member);

            List<Material> materials = new ArrayList<>();
            Material material1 = Material.createMaterial(ResourceType.INGOT, "인코드", 11123);
            Material material2 = Material.createMaterial(ResourceType.SCRAP, "자체", 1200);
            Material material3 = Material.createMaterial(ResourceType.OUTER, "마대", 500);
            Material material4 = Material.createMaterial(ResourceType.OUTER, "재괴", 1300);
            Material material5 = Material.createMaterial(ResourceType.OUTER, "밴딩", 7000);
            materials.add(material1);
            materials.add(material2);
            materials.add(material3);
            materials.add(material4);
            materials.add(material5);


            List<Additive> additives = new ArrayList<>();
            Additive additive1 = Additive.createAdditive("원석", 21);
            Additive additive2 = Additive.createAdditive("모합금", 63);
            Additive additive3 = Additive.createAdditive("MG", 82);
            additives.add(additive1);
            additives.add(additive2);
            additives.add(additive3);

            List<Ingredient> ingredients = new ArrayList<>();
            Ingredient ingredient1 = Ingredient.createIngredient("Fe", 1.333f);
            Ingredient ingredient2 = Ingredient.createIngredient("Cu", 1.333f);
            Ingredient ingredient3 = Ingredient.createIngredient("Zn", 1.333f);
            Ingredient ingredient4 = Ingredient.createIngredient("Mn", 1.333f);
            Ingredient ingredient5 = Ingredient.createIngredient("Si", 1.333f);
            Ingredient ingredient6 = Ingredient.createIngredient("Mg", 1.333f);
            ingredients.add(ingredient1);
            ingredients.add(ingredient2);
            ingredients.add(ingredient3);
            ingredients.add(ingredient4);
            ingredients.add(ingredient5);
            ingredients.add(ingredient6);

            Process process = Process.createProcess(LocalDate.now(), 2, 2, 6803, 7, materials, additives, member);

            FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcess(LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),
                    12, 22, 800, null, null, "컴소공", process, ingredients);

            em.persist(furnaceProcess);


            CastingPreparation castingPreparation = CastingPreparation.createCastingPreparation(1, 1, 1, 1, 1, 1, 1, "테스트2");
            CastingData castingData = CastingData.createCastingData(2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 21, 12, 1, 1, 1, 1);
            CastingTemperature castingTemperature = CastingTemperature.createCastingTemperature(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Billet billet = Billet.createBillet(8123L, 2, 2, "billetMore테스트2");
            Casting casting = Casting.createCasting(LocalDateTime.now(), LocalDateTime.now(), "김현석", "박형준","특이사항2", process, castingPreparation, castingData, castingTemperature, billet);

            em.persist(casting);


        }
    }




}
