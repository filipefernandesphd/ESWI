package bank.repository;

import bank.domain.Identifiable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TextRepository<T extends Identifiable> implements Repository<T> {
    private final Path path;
    private final TextMapper<T> mapper;

    public TextRepository(Path path, TextMapper<T> mapper) {
        this.path = path;
        this.mapper = mapper;
    }

    @Override
    public List<T> findAll() {
        ensureFile();
        try {
            List<T> items = new ArrayList<>();
            for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
                if (!line.trim().isEmpty()) {
                    items.add(mapper.fromLine(line));
                }
            }
            return items;
        } catch (IOException exception) {
            throw new IllegalStateException("Falha ao ler arquivo " + path, exception);
        }
    }

    @Override
    public Optional<T> findById(String id) {
        for (T item : findAll()) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(T item) {
        List<T> items = new ArrayList<>();
        items.add(item);
        saveAll(items);
    }

    @Override
    public void saveAll(Collection<T> items) {
        Map<String, T> indexed = new LinkedHashMap<>();
        for (T existing : findAll()) {
            indexed.put(existing.getId(), existing);
        }
        for (T item : items) {
            indexed.put(item.getId(), item);
        }
        writeAll(indexed.values());
    }

    @Override
    public void deleteById(String id) {
        Map<String, T> indexed = new LinkedHashMap<>();
        for (T item : findAll()) {
            if (!item.getId().equals(id)) {
                indexed.put(item.getId(), item);
            }
        }
        writeAll(indexed.values());
    }

    private void writeAll(Collection<T> items) {
        ensureDirectory();
        List<String> lines = new ArrayList<>();
        for (T item : items) {
            lines.add(mapper.toLine(item));
        }
        try {
            Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException exception) {
            throw new IllegalStateException("Falha ao gravar arquivo " + path, exception);
        }
    }

    private void ensureFile() {
        ensureDirectory();
        if (!Files.exists(path)) {
            try {
                Files.write(path, new ArrayList<String>(), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            } catch (IOException exception) {
                throw new IllegalStateException("Falha ao criar arquivo " + path, exception);
            }
        }
    }

    private void ensureDirectory() {
        Path parent = path.getParent();
        if (parent != null) {
            try {
                Files.createDirectories(parent);
            } catch (IOException exception) {
                throw new IllegalStateException("Falha ao criar diretorio " + parent, exception);
            }
        }
    }
}
