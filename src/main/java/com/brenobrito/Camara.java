package com.brenobrito;

import java.util.ArrayList;
import java.util.List;

public class Camara implements Setor
{

  private final String nome;
  private final List<Setor> setores = new ArrayList<>();

  public Camara(String nome)
  {
    this.nome = nome;
  }

  public void escavar(Setor setor)
  {
    if (setor == this)
    {
      throw new IllegalArgumentException("Uma camara nao pode conter a si mesma");
    }
    this.setores.add(setor);
  }

  @Override
  public String getNome()
  {
    return this.nome;
  }

  @Override
  public int populacao()
  {
    int total = 0;
    for (Setor setor : this.setores)
    {
      total += setor.populacao();
    }
    return total;
  }

  @Override
  public int profundidade()
  {
    int maior = 0;
    for (Setor setor : this.setores)
    {
      maior = Math.max(maior, setor.profundidade());
    }
    return maior + 1;
  }

  @Override
  public int galerias()
  {
    int total = 0;
    for (Setor setor : this.setores)
    {
      total += setor.galerias();
    }
    return total;
  }
}
