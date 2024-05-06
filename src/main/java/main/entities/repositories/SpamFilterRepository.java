package main.entities.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpamFilterRepository implements ISpamFilterRepository {
    public static final List<String> black_list_domains = new ArrayList<>(Arrays.asList("domain1.com", "domain2.com", "domain3.com"));
    public static final List<String> white_list_domains = new ArrayList<>(Arrays.asList("domain1.com", "domain2.com", "domain3.com"));
    public static final List<String> black_list_content = new ArrayList<>(Arrays.asList("domain1.com", "domain2.com", "domain3.com"));

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
