package com.brenobrito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CamaraTest
{

  private Camara formigueiro;
  private Camara subsolo;
  private Camara cria;

  @BeforeEach
  void escavarOFormigueiro()
  {
    this.cria = new Camara("Camara de cria");
    cria.escavar(new Galeria("Tunel da cria", 40));
    cria.escavar(new Galeria("Tunel umido", 25));

    Camara deposito = new Camara("Deposito de graos");
    deposito.escavar(new Galeria("Tunel do deposito", 15));

    this.subsolo = new Camara("Subsolo");
    subsolo.escavar(cria);
    subsolo.escavar(deposito);

    this.formigueiro = new Camara("Formigueiro");
    formigueiro.escavar(new Galeria("Entrada", 10));
    formigueiro.escavar(subsolo);
  }

  @Test
  void deveSomarAPopulacaoDoFormigueiroInteiro()
  {
    assertEquals(90, formigueiro.populacao());
  }

  @Test
  void deveSomarAPopulacaoDeUmaSubarvore()
  {
    assertEquals(80, subsolo.populacao());
    assertEquals(65, cria.populacao());
  }

  @Test
  void deveTratarGaleriaECamaraDaMesmaForma()
  {
    Setor galeria = new Galeria("Tunel solitario", 7);

    Camara camara = new Camara("Camara rasa");
    camara.escavar(new Galeria("Tunel interno", 7));

    assertEquals(galeria.populacao(), camara.populacao());
    assertEquals(galeria.galerias(), camara.galerias());
  }

  @Test
  void deveMedirAProfundidade()
  {
    assertEquals(4, formigueiro.profundidade());
    assertEquals(3, subsolo.profundidade());
    assertEquals(2, cria.profundidade());
    assertEquals(1, new Galeria("Tunel raso", 1).profundidade());
  }

  @Test
  void deveContarTodasAsGalerias()
  {
    assertEquals(4, formigueiro.galerias());
    assertEquals(3, subsolo.galerias());
  }

  @Test
  void deveZerarCamaraVazia()
  {
    Camara vazia = new Camara("Camara abandonada");

    assertEquals(0, vazia.populacao());
    assertEquals(0, vazia.galerias());
    assertEquals(1, vazia.profundidade());
  }

  @Test
  void devePropagarNovaGaleriaAteARaiz()
  {
    cria.escavar(new Galeria("Tunel novo", 20));

    assertEquals(110, formigueiro.populacao());
    assertEquals(5, formigueiro.galerias());
  }

  @Test
  void deveRecusarCamaraDentroDeSiMesma()
  {
    assertThrows(IllegalArgumentException.class, () -> formigueiro.escavar(formigueiro));
  }

  @Test
  void deveRecusarGaleriaComOperariasNegativas()
  {
    assertThrows(IllegalArgumentException.class, () -> new Galeria("Tunel invalido", -1));
  }

  @Test
  void deveGuardarONomeDeCadaSetor()
  {
    assertEquals("Formigueiro", formigueiro.getNome());
    assertEquals("Camara de cria", cria.getNome());
    assertEquals("Entrada", new Galeria("Entrada", 10).getNome());
  }
}
