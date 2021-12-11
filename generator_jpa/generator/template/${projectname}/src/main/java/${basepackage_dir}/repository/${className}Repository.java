<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.repository;

import com.angogo.rpa.console.common.repository.BaseRepository;
import com.angogo.rpa.console.system.api.entity.SysRole;
import org.springframework.stereotype.Repository;

@Repository
public interface ${className}Repository extends BaseRepository<${className}, Long> {


}