package main.entities;

import java.util.List;

public interface ISpamFilterRepository {
    List<String> getBlackListDomains();
    void addBlackListDomain(String domain);

    List<String> getWhiteListDomains();
    void addWhiteListDomain(String domain);

    List<String> getBlackListContent();
    void addBlackListContent(String content);

    void remove(String content);
}
