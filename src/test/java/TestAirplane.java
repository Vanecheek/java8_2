import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TestAirplane {
    List<Airplane> airplanesOblako = createAirplanes(() -> new Airplane("Oblako",450,30,6000),10);
    List<Airplane> airplanesKozak = createAirplanes(() -> new Airplane("Kozak",1000,20,3000),30);

    public List<Airplane> getAirplanesKozak(){
        return airplanesKozak;
    }
    public List<Airplane> createAirplanes(Supplier<Airplane> supplier, int n){
        List<Airplane> airplanes = new LinkedList<>();
        for (int i = 0; i < n; i++){
            airplanes.add(supplier.get());
        }
        return airplanes;
    }
    public void changeAirplane(Airplane airplane, Consumer<Airplane> consumer){
        consumer.accept(airplane);
    }

    //PREDICATE
    @Test
    public void testAirplaneSpeed(){
        List<Airplane> airplanesActual;
        Predicate<Airplane> airplanePredicate = airplane -> airplane.speed > 450;
        airplanesActual = getAirplanesKozak().stream().filter(airplanePredicate).collect(Collectors.toList());
        airplanesActual.forEach(airplane -> {
            Assert.assertTrue(airplane.speed > 450);
        });
    }
    @Test
    public void testAirplaneModel(){
        List<Airplane> airplanesActual;
        Predicate<Airplane> airplanePredicate = airplane -> Objects.equals(airplane.model, "Kozak");
        airplanesActual = getAirplanesKozak().stream().filter(airplanePredicate).collect(Collectors.toList());
        airplanesActual.forEach(airplane -> {
            Assert.assertEquals(airplane.model, "Kozak");
        });
    }

    //FUNCTION
    public int getAvarageData(List<Airplane> airplanes, Function<Airplane, Integer> function){
        int res = 0;
        for (Airplane airplane : airplanes){
            res += function.apply(airplane);
        }
        return res/airplanes.size();
    }
    @Test
    public void testAvarageSpeed(){
        int avarageSpeed = getAvarageData(airplanesOblako, airplane -> airplane.speed);
        Assert.assertEquals(avarageSpeed,450);
    }
    @Test
    public void testAvarageHeight(){
        int avarageHeight = getAvarageData(airplanesOblako, airplane -> airplane.maxHeight);
        Assert.assertEquals(avarageHeight,30);
    }

    //SUPPLIER
    @Test
    public void testCreationAirplane(){
        List<Airplane> airplanesVANEK = createAirplanes(() -> new Airplane("VANEK", 1500, 15, 3000),15);
        for (Airplane airplane : airplanesVANEK) {
            Assert.assertEquals(airplane.model, "VANEK");
            Assert.assertEquals(airplane.speed, 1500);
            Assert.assertEquals(airplane.maxHeight, 15);
            Assert.assertEquals(airplane.maxFlyDistance, 3000);
        }
    }
    @Test
    public void testCreationAirplaneSize(){
        List<Airplane> airplanesVANEK = createAirplanes(() -> new Airplane("VANEK", 1500, 15, 3000),15);
        Assert.assertEquals(airplanesVANEK.size(), 15);
    }


    //CONSUMER
    @Test
    public void testChangeDistanceConsumer(){
        changeAirplane(airplanesKozak.get(2), airplaneToChange -> {
            airplaneToChange.maxFlyDistance = 2500;
        });
        Assert.assertEquals(airplanesKozak.get(2).maxFlyDistance, 2500);
    }

    @Test
    public void testChangeDistanceAndSpeedConsumer(){
        changeAirplane(airplanesKozak.get(2), airplaneToChange -> {
            airplaneToChange.maxFlyDistance = 2500;
            airplaneToChange.speed = 1200;
        });
        Assert.assertEquals(airplanesKozak.get(2).maxFlyDistance, 2500);
        Assert.assertEquals(airplanesKozak.get(2).speed, 1200);
    }

}
