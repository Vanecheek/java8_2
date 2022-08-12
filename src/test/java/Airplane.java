import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Airplane {
    String model;
    int speed;
    int maxHeight;
    int maxFlyDistance;


    @Override
    public String toString() {
        return "Airplane{" +
                "model='" + model + '\'' +
                ", speed=" + speed +
                ", maxHeight=" + maxHeight +
                ", maxFlyDistance=" + maxFlyDistance +
                '}';
    }

    public Airplane(String model, int speed, int maxHeight, int maxFlyDistance) {
        this.model = model;
        this.speed = speed;
        this.maxHeight = maxHeight;
        this.maxFlyDistance = maxFlyDistance;
    }
}
