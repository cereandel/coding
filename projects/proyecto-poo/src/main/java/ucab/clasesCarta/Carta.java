package ucab.clasesCarta;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "idCarta", visible = true)
@JsonSubTypes({
        // cartas azules
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "B0"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "B1"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "B2"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "B3"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "B4"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "B5"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "B6"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "B7"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "B8"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "B9"),
        // cartas rojas
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "R0"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "R1"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "R2"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "R3"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "R4"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "R5"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "R6"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "R7"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "R8"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "R9"),
        // cartas amarillas
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "Y0"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "Y1"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "Y2"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "Y3"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "Y4"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "Y5"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "Y6"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "Y7"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "Y8"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "Y9"),
        // cartas verdes
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "G0"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "G1"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "G2"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "G3"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "G4"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "G5"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "G6"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "G7"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "G8"),
        @JsonSubTypes.Type(value = CartaEnumerada.class, name = "G9"),
        // cartas especiales
        @JsonSubTypes.Type(value = TomaDos.class, name = "BT2"),
        @JsonSubTypes.Type(value = TomaDos.class, name = "RT2"),
        @JsonSubTypes.Type(value = TomaDos.class, name = "YT2"),
        @JsonSubTypes.Type(value = TomaDos.class, name = "GT2"),
        @JsonSubTypes.Type(value = TomaCuatro.class, name = "CT4"),
        @JsonSubTypes.Type(value = CambiarColor.class, name = "CC"),
        @JsonSubTypes.Type(value = CambiarSentido.class, name = "BR"),
        @JsonSubTypes.Type(value = CambiarSentido.class, name = "RR"),
        @JsonSubTypes.Type(value = CambiarSentido.class, name = "YR"),
        @JsonSubTypes.Type(value = CambiarSentido.class, name = "GR"),
        @JsonSubTypes.Type(value = PierdeTurno.class, name = "BS"),
        @JsonSubTypes.Type(value = PierdeTurno.class, name = "RS"),
        @JsonSubTypes.Type(value = PierdeTurno.class, name = "YS"),
        @JsonSubTypes.Type(value = PierdeTurno.class, name = "GS"),
})
public abstract class Carta {
    private String idCarta;
    private char color;

    public Carta() {
    }

    public String getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(String idCarta) {
        this.idCarta = idCarta;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public Carta(String idCarta, char color) {
        this.idCarta = idCarta;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.valueOf(this.color);
    }
}
