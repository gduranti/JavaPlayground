package estudojpa.ed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MUSICAS")
public class MusicaED {

    @Id
    @Column(name = "NRO_INT_MUSICA")
    private Long nroIntMusica;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DURACAO")
    private Integer duracao;

    public Long getNroIntMusica() {
        return nroIntMusica;
    }

    public void setNroIntMusica(Long nroIntMusica) {
        this.nroIntMusica = nroIntMusica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nroIntMusica == null) ? 0 : nroIntMusica.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MusicaED other = (MusicaED) obj;
        if (nroIntMusica == null) {
            if (other.nroIntMusica != null)
                return false;
        } else if (!nroIntMusica.equals(other.nroIntMusica))
            return false;
        return true;
    }

}
