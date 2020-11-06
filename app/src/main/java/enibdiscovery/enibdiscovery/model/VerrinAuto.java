package enibdiscovery.enibdiscovery.model;

import java.util.List;

public class VerrinAuto {
    private int mVerrinAuto;
    private int mState;
    private int mDirection;
    private List<Integer> mStateImage;
    private List<Integer> mStateDimension;

    public VerrinAuto(int verrinAuto, int state, int direction, List<Integer> stateImage, List<Integer> stateDimension){ //1 up, 2 right, 3 down, 4 left
        this.setVerrinAuto(verrinAuto);
        this.setState(state);
        this.setDirection(direction);
        this.setStateImage(stateImage);
        this.setStateDimension(stateDimension);
    }
    public int getVerrinAuto() {return mVerrinAuto;}
    public void setVerrinAuto(int verrinAuto) {
        mVerrinAuto = verrinAuto;
    }

    public int getState() {return mState;}
    public void setState(int state) {
        mState = state;
    }

    public List<Integer> getStateImage() {
        return mStateImage;
    }
    public void setStateImage(List<Integer> stateImage) {
        if (stateImage == null) {
            throw new IllegalArgumentException("Impossible null");
        }
        mStateImage = stateImage;
    }
    public int getDirection() {return mDirection;}
    public void setDirection(int direction) {
        mDirection = direction;
    }

    public List<Integer> getStateDimension() {
        return mStateDimension;
    }
    public void setStateDimension(List<Integer> stateDimension) {
        if (stateDimension == null) {
            throw new IllegalArgumentException("Impossible null");
        }
        mStateDimension = stateDimension;
    }


}