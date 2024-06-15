package com.example.myappavia.data.API.ModelTicket;

import com.google.gson.annotations.SerializedName;

public class ResponseIATAs {
    @SerializedName("origin")
    private OriginCode origin;
    @SerializedName("destination")
    private DestinationCode destination;

    public ResponseIATAs(OriginCode origin, DestinationCode destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public OriginCode getOrigin() {
        return origin;
    }

    public void setOrigin(OriginCode origin) {
        this.origin = origin;
    }

    public DestinationCode getDestination() {
        return destination;
    }

    public void setDestination(DestinationCode destination) {
        this.destination = destination;
    }
}
