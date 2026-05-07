package catcafe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatCafeTest {

    private CatCafe cafe;

    @BeforeEach
    void setup() {
        cafe = new CatCafe();
    }

    @Test
    void empty_cafe_when_adding_one_cat_then_cat_count_is_one() {
        // given
        var cat = new FelineOverLord("Simba", 4);

        // when
        cafe.addCat(cat);

        // then
        assertEquals(1, cafe.getCatCount());
    }

    @Test
    void add_cat_and_count() {
        // given
        cafe.addCat(new FelineOverLord("Simba", 4));
        cafe.addCat(new FelineOverLord("Milo", 5));
        cafe.addCat(new FelineOverLord("Nala", 3));

        // when
        var count = cafe.getCatCount();

        // then
        assertEquals(3, count);
    }

    @Test
    void existing_cat_name_when_searching_then_correct_cat_is_returned() {
        // given
        var cat = new FelineOverLord("Simba", 6);
        cafe.addCat(cat);

        // when
        var result = cafe.getCatByName("Simba");

        // then
        assertEquals(cat, result);
    }

    @Test
    void unknown_cat_name_when_searching_then_null_is_returned() {
        // given
        cafe.addCat(new FelineOverLord("Simba", 4));

        // when
        var result = cafe.getCatByName("Tiger");

        // then
        assertNull(result);
    }

    @Test
    void weight_range_when_searching_then_matching_cat_is_returned() {
        // given
        var cat = new FelineOverLord("Simba", 8);
        cafe.addCat(cat);

        // when
        var result = cafe.getCatByWeight(7, 10);

        // then
        assertEquals(cat, result);
    }

    @Test
    void weight_range_without_matching_cat_when_searching_then_null_is_returned() {
        // given
        cafe.addCat(new FelineOverLord("Simba", 3));

        // when
        var result = cafe.getCatByWeight(5, 10);

        // then
        assertNull(result);
    }

    @Test
    void negative_min_weight_when_searching_then_null_is_returned() {
        // given
        cafe.addCat(new FelineOverLord("Simba", 4));

        // when
        var result = cafe.getCatByWeight(-1, 5);

        // then
        assertNull(result);
    }

    @Test
    void invalid_weight_range_when_searching_then_null_is_returned() {
        // given
        cafe.addCat(new FelineOverLord("Simba", 6));

        // when
        var result = cafe.getCatByWeight(10, 5);

        // then
        assertNull(result);
    }

    @Test
    void empty_cafe_when_searching_by_weight_then_null_is_returned() {
        // given

        // when
        var result = cafe.getCatByWeight(1, 10);

        // then
        assertNull(result);
    }

    @Test
    void duplicate_cat_names_when_searching_then_first_matching_cat_is_returned() {
        // given
        var firstCat = new FelineOverLord("Loki", 4);
        var secondCat = new FelineOverLord("Loki", 7);

        cafe.addCat(firstCat);
        cafe.addCat(secondCat);

        // when
        var result = cafe.getCatByName("Loki");

        // then
        assertEquals(firstCat, result);
    }
}
