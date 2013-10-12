package com.skyline.model.utils;
import java.util.Objects;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for all entities (later to be stored in database), 
 * Product, Order, etc
 * @author hajo
 * 
 * @modified by anno
 */

@MappedSuperclass
public abstract class AbstractEntity{
    
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    @Column(nullable=false)
    private Long id;
   
    protected AbstractEntity(){
    }
    
    protected AbstractEntity(Long id){
        this.id = id;
    }
    
    public Long getId(){
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
