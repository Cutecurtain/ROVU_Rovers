package Area;

public class Area  implements IArea{

    private IShape shape;

    private boolean physical;

    public boolean isPhysical() {
        return physical;
    }


    public boolean isPosIn() {
        return false;
    }
}
