package com.UI.EnrollmentDetails;

import androidx.lifecycle.ViewModel;

public class EnrollmentDetailsViewModel extends ViewModel {
    public byte[] rightThumb;
    public byte[] rightIndex;
    public byte[] leftThumb;
    public byte[] leftIndex;

    public byte[] getRightThumb() {
        return rightThumb;
    }

    public void setRightThumb(byte[] rightThumb) {
        this.rightThumb = rightThumb;
    }

    public byte[] getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(byte[] rightIndex) {
        this.rightIndex = rightIndex;
    }

    public byte[] getLeftThumb() {
        return leftThumb;
    }

    public void setLeftThumb(byte[] leftThumb) {
        this.leftThumb = leftThumb;
    }

    public byte[] getLeftIndex() {
        return leftIndex;
    }

    public void setLeftIndex(byte[] leftIndex) {
        this.leftIndex = leftIndex;
    }
}
