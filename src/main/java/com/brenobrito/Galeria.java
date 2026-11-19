package com.brenobrito;

public class Galeria implements Setor
{

  private final String nome;
  private final int operarias;

  public Galeria(String nome, int operarias)
  {
    if (operarias < 0)
    {
      throw new IllegalArgumentException("Uma galeria nao pode ter operarias negativas");
    }
    this.nome = nome;
    this.operarias = operarias;
  }

  @Override
  public String getNome()
  {
    return this.nome;
  }

  @Override
  public int populacao()
  {
    return this.operarias;
  }

  @Override
  public int profundidade()
  {
    return 1;
  }

  @Override
  public int galerias()
  {
    return 1;
  }
}
