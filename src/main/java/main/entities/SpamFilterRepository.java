package main.entities;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpamFilterRepository implements ISpamFilterRepository {
    public static final List<String> black_list_domains = new ArrayList<>();
    public static final List<String> white_list_domains = new ArrayList<>();
    public static final List<String> black_list_content = new ArrayList<>();

    @Override
    public List<String> getBlackListDomains() {
        return black_list_domains;
    }

    @Override
    public void addBlackListDomain(String domain) {
        black_list_domains.add(domain);
    }

    @Override
    public List<String> getWhiteListDomains() {
        return white_list_domains;
    }

    @Override
    public void addWhiteListDomain(String domain) {
        white_list_domains.add(domain);
    }

    @Override
    public List<String> getBlackListContent() {
        return black_list_content;
    }

    @Override
    public void addBlackListContent(String content) {
        black_list_content.add(content);
    }

    @Override
    public void remove(String content) {
        black_list_content.remove(content);
    }
}
