
package Objects;

public class CommentaryTurn {
    private String commentary_incident;
    
    public CommentaryTurn(){}
    
    public CommentaryTurn(String commentary_turn){
        this.commentary_incident = commentary_turn;
    }
    
    public String getCommentary_incident() {
        return commentary_incident;
    }
    
    public void setCommentary_Incident(String descripcion) {
        this.commentary_incident = descripcion;
    }

    @Override
    public String toString() {
        return commentary_incident;
    }
    
    
}
