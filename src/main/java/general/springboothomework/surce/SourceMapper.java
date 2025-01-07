package general.springboothomework.surce;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SourceMapper {

    Source toSource(Destination destination);
    Destination toDestination(Source source);

    List<Source> toSourceList(List<Destination> destinations);
    List<Destination> toDestinationList(List<Source> sources);
}
