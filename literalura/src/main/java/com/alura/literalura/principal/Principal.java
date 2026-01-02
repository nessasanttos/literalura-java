package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumoApi;

import java.util.Scanner;

public class Principal {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;
    private final Scanner scanner = new Scanner(System.in);

    public Principal(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    public void exibeMenu() {

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                    
                    ===== LITERALURA =====
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair
                    """);

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {
                case 1 -> buscarLivroPorTitulo();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivosPorAno();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroPorTitulo() {

        System.out.print("Digite o título do livro: ");
        String tituloBuscado = scanner.nextLine();

        ConsumoApi consumoApi = new ConsumoApi();
        var json = consumoApi.buscarLivro(tituloBuscado);

        if (json.get("results").isEmpty()) {
            System.out.println("Livro não encontrado.");
            return;
        }

        var livroJson = json.get("results").get(0);

        String titulo = livroJson.get("title").asText();

        var autorJson = livroJson.get("authors").get(0);
        String nomeAutor = autorJson.get("name").asText();
        Integer nascimento = autorJson.get("birth_year").asInt();
        Integer falecimento = autorJson.get("death_year").asInt();

        String idioma = livroJson.get("languages").get(0).asText();
        Integer downloads = livroJson.get("download_count").asInt();

        Autor autor = autorRepository.findByNome(nomeAutor);
        if (autor == null) {
            autor = new Autor(nomeAutor, nascimento, falecimento);
            autorRepository.save(autor);
        }

        Livro livro = new Livro(titulo, idioma, downloads, autor);
        livroRepository.save(livro);

        System.out.println("\nLivro salvo com sucesso!");
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + nomeAutor);
    }

    private void listarLivros() {
        livroRepository.findAll()
                .forEach(l -> System.out.println(l.getTitulo()));
    }

    private void listarAutores() {
        autorRepository.findAll()
                .forEach(a -> System.out.println(a.getNome()));
    }

    private void listarAutoresVivosPorAno() {

        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        autorRepository
                .findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano)
                .forEach(a -> {
                    System.out.println("\nAutor: " + a.getNome());
                    System.out.println("Ano de nascimento: " + a.getAnoNascimento());
                    System.out.println("Ano de falecimento: " + a.getAnoFalecimento());
                });
    }


    private void listarLivrosPorIdioma() {

        System.out.print("Digite o idioma (ex: en, pt, es): ");
        String idioma = scanner.nextLine();

        var livros = livroRepository.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado nesse idioma.");
            return;
        }

        livros.forEach(l -> {
            System.out.println("\nTítulo: " + l.getTitulo());
            System.out.println("Autor: " + l.getAutor().getNome());
            System.out.println("Idioma: " + l.getIdioma());
            System.out.println("Downloads: " + l.getDownloads());
        });
    }

}

